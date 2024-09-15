package com.example.property.dto.property_request;

import com.example.property.enumuration.UnitType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnitRequestDto {
    private Long id;

    @NotNull(message = "Building id not be null!")
    @DecimalMin(value = "1",message = "Building id 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private Long buildingId;

    @NotNull(message = "Unit count room not be null!")
    @Min(value = 1,message = "Count room 1-dən daha böyük və ya kiçik tam ədəd olmalıdır!")
    private int countRoom;

    @NotNull(message = "Unit area not be null!")
    @DecimalMin(value = "1",message = "Area 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private float area;

    @NotNull(message = "Unit floor unit not be null!")
    @Min(value = 1,message = "Unit floor 1-dən daha böyük və ya kiçik tam ədəd olmalıdır!")
    private int floor;

    @Size(max = 10)
    @NotNull(message = "Unit number not be null!")
    private String unitNumber;

    @Size(max = 100)
    @NotNull(message = "Unit notes not be null!")
    private String note;

    @Enumerated(EnumType.STRING)
    private UnitType unitType;
}
