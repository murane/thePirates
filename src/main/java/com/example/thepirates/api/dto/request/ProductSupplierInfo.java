package com.example.thepirates.api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@BussinessHour
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductSupplierInfo {
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime open;
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime close;

    public ProductSupplierInfo(LocalTime open, LocalTime close) {
        this.open = open;
        this.close = close;
    }
}
