package com.example.thepirates.api.dto;

import lombok.*;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOptionInfo {
    private String name;
    private int price;
    private int stock;

    @Builder
    public ProductOptionInfo(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
