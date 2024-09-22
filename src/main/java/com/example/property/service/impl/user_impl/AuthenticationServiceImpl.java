package com.example.property.service.impl.user_impl;

import com.example.property.dto.user.AuthenticationRequest;
import com.example.property.dto.user.AuthenticationResponse;
import com.example.property.dto.user.RegisterRequest;
import com.example.property.entity.user.User;
import com.example.property.entity.user.UserGrantedRoles;
import com.example.property.enumuration.TokenType;
import com.example.property.enumuration.UserPermissions;
import com.example.property.exception.AuthenticationException;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.repository.role.CompanyRepository;
import com.example.property.repository.role.CompanyRolesRepository;
import com.example.property.repository.role.UserGrantedRoleRepository;
import com.example.property.repository.user.TokenRepository;
import com.example.property.repository.user.UserRepository;
import com.example.property.service.user.AuthenticationService;
import com.example.property.service.impl.config_service_impl.JwtService;
import com.example.property.token.Token;
import com.example.property.validator.ObjectValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ObjectValidator<AuthenticationRequest> validator;
    private final ObjectValidator<RegisterRequest> requestObjectValidator;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final CompanyRolesRepository companyRolesRepository;
    private final CompanyRepository companyRepository;
    private final UserGrantedRoleRepository userGrantedRoleRepository;

    public AuthenticationResponse register(RegisterRequest request) throws MethodArgumentNotValidException {

        requestObjectValidator.validate(request);
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new MethodArgumentNotValidException("Username Already Defined " + request.getUsername());
        }

        var user = User.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .email(request.getEmail())
                .phoneNumber1(request.getPhoneNumber1())
                .phoneNumber2(request.getPhoneNumber2())
                .password(passwordEncoder.encode(request.getPassword()))
                .companyOfUsers(companyRepository.getReferenceById(request.getCompany_id()))
                .build();
        Set<SimpleGrantedAuthority> authorityList = new HashSet<>();

        for (UserPermissions role : UserPermissions.values()) {
            authorityList.add(new SimpleGrantedAuthority(role.name()));

        }
        user.addPermissions(authorityList);
        user.getAuthorities();

        System.out.println(authorityList + "llllllll");

        UserGrantedRoles userGrantedRoles = new UserGrantedRoles();
        userGrantedRoles.setUserRole("USER");
        userGrantedRoles.setUser(user);

        user.setUserGrantedRoles(List.of(userGrantedRoles));

        var savedUser = userRepository.save(user);
        userGrantedRoleRepository.save(userGrantedRoles);
        return getRegisterPermissionJwt(user);
    }



    public AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationException {
        validator.validate(request);

        if (!userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new AuthenticationException("Username or password not correct with username: " + request.getUsername());
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        return getPermissionJwt(user);
    }

    private void savedUserToken(User user, String jwtToken, String refreshToken) {
        var validUserTokens = tokenRepository.finAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        var tokenEntity = tokenRepository.findByUser(user);
        tokenEntity.setUser(user);
        tokenEntity.setToken(jwtToken);
        tokenEntity.setRefreshToken(refreshToken);
        tokenEntity.setTokenType(TokenType.BEARER);
        tokenEntity.setRevoked(false);
        tokenEntity.setExpired(false);
        tokenRepository.save(tokenEntity);
    }

    private void savedMainUserToken(User user, String jwtToken, String refreshToken) {

        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .refreshToken(refreshToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    public AuthenticationResponse refreshToken(HttpServletRequest request,
                             HttpServletResponse response) throws AuthenticationException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new AuthenticationException("Jwt token is not Valid");
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            var user = this.userRepository.findByUsername(username).orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                return getPermissionJwt(user);
            }
        }
    throw new AuthenticationException("Jwt token is not Valid");
    }
    public AuthenticationResponse getPermissionJwt(UserDetails userDetails){
        Map<String,Object> extraClaims = new HashMap<>();
        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        extraClaims.put("authorities", authorities);
        var accessToken = jwtService.generateToken(extraClaims,userDetails);
        var newRefreshToken = jwtService.generateRefreshToken(extraClaims,userDetails);
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        savedUserToken(user, accessToken, newRefreshToken);
     return AuthenticationResponse
                .builder()
                .email(user.getEmail())
                .fullName(user.getFullName())
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .build();

    }

    public AuthenticationResponse getRegisterPermissionJwt(UserDetails userDetails){
        Map<String,Object> extraClaims = new HashMap<>();
        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        extraClaims.put("authorities", authorities);
        var accessToken = jwtService.generateToken(extraClaims,userDetails);
        var newRefreshToken = jwtService.generateRefreshToken(extraClaims,userDetails);
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        savedMainUserToken(user, accessToken, newRefreshToken);
        return AuthenticationResponse
                .builder()
                .email(user.getEmail())
                .fullName(user.getFullName())
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}

