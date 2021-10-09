package com.example.thepirates.api.dto.request;

import com.example.thepirates.domain.DeliveryType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDeliveryInfo {
    private int price;
    private DeliveryType type;
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalTime closing;

    @Builder
    public ProductDeliveryInfo(int price, DeliveryType type, LocalTime closing) {
        this.price = price;
        this.type = type;
        this.closing = closing;
    }
}
