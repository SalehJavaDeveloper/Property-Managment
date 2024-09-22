package com.example.property.dto.property_request;

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
public class BuildingRequestDto {

    private Long id;

    @Size(max = 30)
    @NotNull(message = "Building name not be null!")
    private String name;

    @Size(max = 50)
    @NotNull(message = "Building description not be null!")
    private String description;

    private ResponsiblePersonDto[] respPerson;

    @NotNull(message = "Building count unit not be null!")
    @Min(value = 1,message = "Count unit 1-dən daha böyük və ya kiçik tam ədəd olmalıdır!")
    private int countUnit;

    @NotNull(message = "Building count entrance not be null!")
    @Min(value = 1,message = "Count entrance 1-dən daha böyük və ya kiçik tam ədəd olmalıdır!")
    private int countEntrance;

    @NotNull(message = "Building count floor not be null!")
    @Min(value = 1,message = "Count floor 1-dən daha böyük və ya kiçik tam ədəd olmalıdır!")
    private int countFloor;

    @NotNull(message = "Building area not be null!")
    @DecimalMin(value = "1",message = "Area 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private double area;

    @Size(max = 100)
    @NotNull(message = "Building name not be null!")
    private String note;

    @NotNull(message = "Property id not be null!")
    @DecimalMin(value = "1",message = "Property id 1-ə bərabər və ya böyük ədəd olmalıdır!")
    private Long propertyId;

}
