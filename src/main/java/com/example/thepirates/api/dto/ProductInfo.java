package com.example.thepirates.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductInfo {
    private String name;

    private String description;

    private String price;

    @Builder
    public ProductInfo(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
