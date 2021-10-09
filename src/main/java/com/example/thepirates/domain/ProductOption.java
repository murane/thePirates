package com.example.thepirates.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Product product;

    @Builder
    public ProductOption(Long id, String name, Integer price,
                         Integer stock, Product product) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.product = product;
    }
}
