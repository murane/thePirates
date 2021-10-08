package com.example.thepirates.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryType {
    FAST("당일"),REGULAR("익일");

    private final String description;
}
