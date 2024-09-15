package com.example.property.service.impl.user_impl;

import com.example.property.dto.user.AuthenticationRequest;
import com.example.property.dto.user.AuthenticationResponse;
import com.example.property.dto.user.RegisterRequest;
import com.example.property.entity.user.User;
import com.example.property.entity.user.UserGrantedAuthority;
import com.example.property.enumuration.TokenType;
import com.example.property.exception.AuthenticationException;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.repository.role.CompanyRepository;
import com.example.property.repository.user.RoleRepository;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

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
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final CompanyRepository companyRepository;

    public AuthenticationResponse register(RegisterRequest request) throws MethodArgumentNotValidException {

        requestObjectValidator.validate(request);
        log.info("bir");
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new MethodArgumentNotValidException("Username Already Defined " + request.getUsername());
        }
        log.info("iki");
        var user = User.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .email(request.getEmail())
                .phoneNumber1(request.getPhoneNumber1())
                .phoneNumber2(request.getPhoneNumber2())
                .password(passwordEncoder.encode(request.getPassword()))
                .companyOfUsers(companyRepository.getReferenceById(request.getCompany_id()))
                .build();



        System.out.println(user.getCompanyOfUsers());

        var grantedAuthority = UserGrantedAuthority.builder()
                .authority(request.getRoleName())
                .mainUser(user)
                .build();

        user.setRoleMain(Set.of(grantedAuthority));
        var savedUser = userRepository.save(user);
        roleRepository.save(grantedAuthority);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        savedMainUserToken(savedUser, jwtToken, refreshToken);
        return AuthenticationResponse
                .builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();

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
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        savedUserToken(user, jwtToken, refreshToken);
        return AuthenticationResponse
                .builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
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
                var accessToken = jwtService.generateToken(user);
                var newRefreshToken = jwtService.generateRefreshToken(user);
                savedUserToken(user, accessToken, newRefreshToken);
                var authResponse = AuthenticationResponse
                        .builder()
                        .accessToken(accessToken)
                        .refreshToken(newRefreshToken)
                        .build();
                return authResponse;
            }
        }
        throw new AuthenticationException("Jwt token is not Valid");
    }
}