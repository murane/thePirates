package com.example.thepirates.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {
    @Column(name = "delivery_fee")
    private Integer fee;
    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_type")
    private DeliveryType type;
    @Column(name = "delivery_closing")
    private LocalTime closing;

    @Builder
    public Delivery(Integer fee, DeliveryType type, LocalTime closing) {
        this.fee = fee;
        this.type = type;
        this.closing = closing;
    }
}
