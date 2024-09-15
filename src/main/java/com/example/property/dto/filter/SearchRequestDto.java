package com.example.property.dto.filter;

import com.example.property.enumuration.FilterOperator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {

    String column;
    String value;
    FilterOperator filterOperator;
    String firstJoinTable;
    String secondJoinTable;
    String thirdJoinTable;
    String fourthJoinTable;
}
