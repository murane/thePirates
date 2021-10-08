package com.example.thepirates.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer deliveryFee;

    private DeliveryType deliveryType;

    private LocalDateTime closing;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProductOption> productOptions = new ArrayList<>();

    @Builder
    public Product(Long id, String name, String description, Integer deliveryFee,
                   DeliveryType deliveryType, LocalDateTime closing) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deliveryFee = deliveryFee;
        this.deliveryType = deliveryType;
        this.closing = closing;
    }

    public void addOption(@NotNull ProductOption option){
        productOptions.add(option);
    }

    public void deleteOption(@NotNull ProductOption option){
        productOptions.remove(option);
    }
}
