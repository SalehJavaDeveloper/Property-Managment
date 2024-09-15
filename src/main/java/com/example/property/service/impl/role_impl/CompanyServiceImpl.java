package com.example.property.service.impl.role_impl;

import com.example.property.dto.role_request.CompanyRequestDto;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.mapper.role.CompanyMapper;
import com.example.property.repository.role.CompanyRepository;
import com.example.property.service.role.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    @Override
    public void saveCompany(CompanyRequestDto companyRequestDto) {
        if(companyRequestDto == null){
            throw new MethodArgumentNotValidException(("Data Arguments is not valid!"));
        }
        companyRepository.save(companyMapper.fromDTO(companyRequestDto));
    }
}
