package com.example.property.dto.communication_request;

import com.example.property.enumuration.MessageStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunicationDto {
    private Long id;
    @NotNull(message = "Communication subject not be null!")
    @NotEmpty(message = "Communication subject not be empty!")
    private String subject;

    @NotNull(message = "Communication content not be null!")
    @NotEmpty(message = "Communication content not be empty!")
    private String content;

    @Enumerated(EnumType.STRING)
    private MessageStatus messageStatus;

//    private Long createdBy;
//
//    private Date createdDate;
//
//    private Long lastModifiedBy;
//
//    private Date lastModifiedDate;
}
