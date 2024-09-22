package com.example.property.service.impl.role_impl;

import com.example.property.dto.role_request.UserGrantedRequestDto;
import com.example.property.dto.role_response.UserGrantedResponseDto;
import com.example.property.dto.user.AuthenticationResponse;
import com.example.property.entity.companyRoles.CompanyRoles;
import com.example.property.entity.user.User;
import com.example.property.entity.user.UserGrantedRoles;
import com.example.property.exception.AuthenticationException;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.mapper.role.UserGrantedRolesMapper;
import com.example.property.repository.role.CompanyRolesRepository;
import com.example.property.repository.role.UserGrantedRoleRepository;
import com.example.property.repository.user.UserRepository;
import com.example.property.service.impl.user_impl.AuthenticationServiceImpl;
import com.example.property.service.role.UserGrantedRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserGrantedRolesServiceImpl implements UserGrantedRoleService {

    private final UserRepository userRepository;
    private final UserGrantedRolesMapper userGrantedRolesMapper;
    private final UserGrantedRoleRepository userGrantedRoleRepository;

    private final CompanyRolesRepository companyRolesRepository;

    private final AuthenticationServiceImpl authenticationService;

    @Override
    public AuthenticationResponse saveUserGrantedRole(UserGrantedRequestDto userGrantedRequestDto) {
        if (userGrantedRequestDto == null) {
            throw new MethodArgumentNotValidException(("Data Arguments is not valid!"));
        }
        Boolean bool = true;
        List<CompanyRoles> companyRoles = userRepository.getReferenceById(userGrantedRequestDto.getUser_id()).getCompanyOfUsers().getCompanyRoles();
        for (CompanyRoles companyRoles1 : companyRoles) {
            if (companyRoles1.getCompanyRoleName().equals(userGrantedRequestDto.getUserRole())) {
                bool = false;
                UserGrantedRoles userGrantedRoles = userGrantedRolesMapper.fromDTO(userGrantedRequestDto);
                User user = userRepository.getReferenceById(userGrantedRequestDto.getUser_id());
                userGrantedRoles.setUser(user);
                Long userGrantedRoleId = userGrantedRoleRepository.findByUserId(user.getId()).getId();
                userGrantedRoles.setId(userGrantedRoleId);
                userGrantedRoleRepository.save(userGrantedRoles);
                List<CompanyRoles> companyRolesList = companyRolesRepository.findCompanyRolesByCompanyRoleName(userGrantedRequestDto.getUserRole());
                Set<SimpleGrantedAuthority> collectPermissions = companyRolesList.stream()
                        .map(companyRole -> new SimpleGrantedAuthority(companyRole.getPermissions().toString()))
                        .collect(Collectors.toSet());
                System.out.println(collectPermissions);
                Set<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();
                user.addPermissions(simpleGrantedAuthorities);
                user.getAuthorities();
                user.addPermissions(collectPermissions);
                user.getAuthorities();
            }
        }
        if (bool) {
            throw new AuthenticationException("This role is not included your company's roles!");
        }
        return authenticationService.getPermissionJwt(userRepository.getReferenceById(userGrantedRequestDto.getUser_id()));
    }

    @Override
    public UserGrantedResponseDto findUserGrantedRoleById(Long id) {
        if(userGrantedRoleRepository.findById(id).isEmpty()){
            throw new MethodArgumentNotValidException("Data not found with specified User Role!");
        }
        return userGrantedRolesMapper.toDTO(userGrantedRoleRepository.getReferenceById(id));
    }

    @Override
    public List<UserGrantedResponseDto> findAll() {
        return userGrantedRolesMapper.toDTOList(userGrantedRoleRepository.findAll());
    }

//    public List<SimpleGrantedAuthority> setRolesForUser() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long  userId = ((User)principal).getId();
//        System.out.println(userId);
//        List<UserGrantedRoles> userGrantedRoles = userRepository.getReferenceById(userId).getUserGrantedRoles();
//      //  List<CompanyRoles> companyRoles = userGrantedRoleRepository.findByUserId(userId).getUser().getCompanyOfUsers().getCompanyRoles();
//        List<CompanyRoles> companyRoles = companyRolesRepository.findCompanyRolesByCompanyRoleName(userGrantedRoles.get(0).getUserRole());
//        return companyRoles.stream()
//                .map(companyRole -> new SimpleGrantedAuthority(companyRole.getPermissions().toString()))
//                .collect(Collectors.toList());
//    }
}
