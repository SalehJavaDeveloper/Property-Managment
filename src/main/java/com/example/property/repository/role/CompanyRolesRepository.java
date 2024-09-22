package com.example.property.repository.role;

import com.example.property.entity.companyRoles.CompanyRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRolesRepository extends JpaRepository<CompanyRoles, Long> {

    List<CompanyRoles> findCompanyRolesByCompanyRoleName(String companyRolesName);
}
