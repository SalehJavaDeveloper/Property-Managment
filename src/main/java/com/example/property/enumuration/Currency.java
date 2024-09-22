package com.example.property.enumuration;

import lombok.Getter;

@Getter
public enum Currency {
    AZN(944);

    private final int code;

    Currency(int code) {
        this.code = code;
    }
}
