package com.example.property.entity.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private int amount;

    private int installment;

    private String description;

    private String email;

    private String telephone;


    private String memberId;


    private String orderId;


}
