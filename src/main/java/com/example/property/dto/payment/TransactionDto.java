package com.example.property.dto.payment;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Long id;
    @NotEmpty(message = "The amount should not be empty!")
    private int amount;

    @NotEmpty(message = "The installment should not be empty!")
    private int installment;

    @NotEmpty(message = "The description should not be empty!")
    private String description;

    @NotEmpty(message = "The email should not be empty!")
    private String email;

    @NotEmpty(message = "The telephone should not be empty!")
    private String telephone;

    @NotEmpty(message = "The member ID should not be empty!")
    private String memberId;

    @NotEmpty(message = "The order ID should not be empty!")
    private String orderId;

}
/*
http://173.212.239.87:8080/property/swagger-ui/index.html
    Double amount;
    String description;
    String notes;
    StatusType statusType;
    List<Long> buildingId;
    List<Long> unitId;
    List<Long> propertyId;
    List<ServiceType> serviceType;
 */
