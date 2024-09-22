package com.example.property.entity.payment;

import com.example.property.abstraction.AuditAble;
import com.example.property.enumuration.PaymentType;
import com.example.property.enumuration.ServiceType;
import com.example.property.enumuration.StatusType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment2")
public class PaymentEntity2 {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "recurring_date")
    private Date recurringDate;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "description")
    private String description;

    @Column(name = "notes")
    private String notes;

    @Column(name = "service_type")
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentEntity")
    private List<PaymentUnitEntity2> paymentUnitList;

    @CreationTimestamp
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;
}