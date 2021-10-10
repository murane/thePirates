package com.example.thepirates.service.dto.input;

import com.example.thepirates.api.dto.ProductOptionInfo;
import com.example.thepirates.domain.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOptionCreateInput {
    private Product product;
    private List<ProductOptionInfo> optionInfos;

    public ProductOptionCreateInput(Product product, List<ProductOptionInfo> optionInfos) {
        this.product = product;
        this.optionInfos = optionInfos;
    }
}
