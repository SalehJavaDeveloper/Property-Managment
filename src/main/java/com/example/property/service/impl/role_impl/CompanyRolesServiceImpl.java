package com.example.property.service.impl.role_impl;

import com.example.property.dto.role_request.CompanyRolesRequestDto;
import com.example.property.dto.role_response.CompanyRolesResponseDto;
import com.example.property.entity.companyRoles.Company;
import com.example.property.entity.companyRoles.CompanyRoles;
import com.example.property.entity.companyRoles.Packet;
import com.example.property.entity.companyRoles.Permission;
import com.example.property.exception.AuthenticationException;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.mapper.role.CompanyRolesMapper;
import com.example.property.repository.role.CompanyRepository;
import com.example.property.repository.role.CompanyRolesRepository;
import com.example.property.service.role.CompanyRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyRolesServiceImpl implements CompanyRolesService {

    private final CompanyRolesRepository companyRolesRepository;
    private final CompanyRolesMapper companyRolesMapper;
    private final CompanyRepository companyRepository;

    @Override
    public void saveCompanyRoles(List<CompanyRolesRequestDto> companyRolesRequestDtos) {
        for (CompanyRolesRequestDto companyRolesRequestDto : companyRolesRequestDtos) {
            if (companyRolesRequestDto == null) {
                throw new MethodArgumentNotValidException(("Data Arguments is not valid!"));
            }
            boolean bool = true;
            Company company = companyRepository.getReferenceById(companyRolesRequestDto.getCompany_id());
            Packet packet = company.getPacket();
            List<Permission> permissions = packet.getPermissions();
            for (Permission permission : permissions) {
                if (permission.getPermissions().equals(companyRolesRequestDto.getPermissions())) {
                    bool = false;
                    CompanyRoles companyRoles = companyRolesMapper.fromDTO(companyRolesRequestDto);
                    companyRoles.setCompanyRoles(companyRepository.getReferenceById(companyRolesRequestDto.getCompany_id()));
                    companyRolesRepository.save(companyRoles);
                }
            }
            if(bool){
                throw new AuthenticationException("This permission not included your company's packet");
            }
        }

    }


    @Override
    public CompanyRolesResponseDto findCompanyRolesById(Long id) {
        if(companyRolesRepository.findById(id).isEmpty()){
            throw new MethodArgumentNotValidException("Data not found with specified Company Role!");
        }
        return companyRolesMapper.toDTO(companyRolesRepository.getReferenceById(id));
    }

    @Override
    public List<CompanyRolesResponseDto> findAllCompanyRoles() {
        return companyRolesMapper.toDTOList(companyRolesRepository.findAll());
    }

    @Override
    public List<CompanyRolesResponseDto> findCompanyRolesByName(String companyRolesName) {
        return companyRolesMapper.toDTOList(companyRolesRepository.findCompanyRolesByCompanyRoleName(companyRolesName));
    }
}
