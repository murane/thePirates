package com.example.thepirates.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryType {
    FAST("당일",0),REGULAR("익일",1);

    private String description;

    private int sendDate;
}
