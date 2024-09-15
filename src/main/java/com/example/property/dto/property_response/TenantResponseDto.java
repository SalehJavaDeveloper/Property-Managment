package com.example.property.dto.property_response;

import com.example.property.entity.property.UnitEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TenantResponseDto {

    private Long id;

    private UnitEntity unitEntity;

    private String name;

    private String surname;

    private String fatherName;

    private String phoneNumber1;

    private String phoneNumber2;

    private String homeNumber;

    private Boolean wpPhoneNumber1;

    private Boolean callPhoneNumber1;

    private Boolean smsPhoneNumber1;

    private Boolean wpPhoneNumber2;

    private Boolean callPhoneNumber2;

    private Boolean smsPhoneNumber2;

    private String email;

    private String pin;

    private Long createdBy;

    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;

    private Boolean activate;
}