package com.example.thepirates.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Table(name="PRODUCT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Embedded
    private Delivery delivery;

    private LocalTime supplierOpen;

    private LocalTime supplierClose;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProductOption> productOptions = new ArrayList<>();

    @Builder
    public Product(Long id, String name, String description, Integer deliveryFee,
                   DeliveryType deliveryType, LocalTime deliveryClosing,
                   LocalTime supplierOpen, LocalTime supplierClose) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.delivery = Delivery.builder()
                .closing(deliveryClosing)
                .type(deliveryType)
                .fee(deliveryFee)
                .build();
        this.supplierOpen = supplierOpen;
        this.supplierClose = supplierClose;
    }

    public int getLowestPrice() {
        return productOptions.stream()
                .map(ProductOption::getPrice)
                .min(Integer::compareTo)
                .orElse(0);
    }

    public void addOptions(@NotNull List<ProductOption> options) {
        options.forEach(this::addOption);
    }

    private void addOption(@NotNull ProductOption option) {
        productOptions.add(option);
    }

    public void deleteOption(@NotNull ProductOption option) {
        productOptions.remove(option);
    }
}
