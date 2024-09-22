package com.example.property.enumuration;

import lombok.Getter;

@Getter
public enum Language {
    AZ("az");

    private final String name;

    Language(String name) {
        this.name = name;
    }
}
