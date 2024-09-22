package com.example.property.exception;

import com.example.property.enumuration.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException{
    private Response response;
}
