package com.example.thepirates.api.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReceivingDateInfo {
    private String date;

    public ReceivingDateInfo(String date) {
        this.date = date;
    }
}
