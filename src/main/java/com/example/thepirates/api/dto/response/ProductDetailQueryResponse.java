package com.example.thepirates.api.dto.response;

import com.example.thepirates.api.dto.ProductOptionInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDetailQueryResponse {
    private String name;
    private String description;
    private String deliveryType;
    private List<ProductOptionInfo> options;

    @Builder
    public ProductDetailQueryResponse(String name, String description,
                                      String deliveryType, List<ProductOptionInfo> options) {
        this.name = name;
        this.description = description;
        this.deliveryType = deliveryType;
        this.options = options;
    }
}
