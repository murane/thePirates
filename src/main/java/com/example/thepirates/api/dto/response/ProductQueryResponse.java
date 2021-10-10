package com.example.thepirates.api.dto.response;

import com.example.thepirates.api.dto.ProductInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductQueryResponse {
    private List<ProductInfo> prodcutInfos = new ArrayList<>();

    public ProductQueryResponse(List<ProductInfo> prodcutInfos) {
        this.prodcutInfos = prodcutInfos;
    }
}
