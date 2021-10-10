package com.example.thepirates.api.dto.request;

import com.example.thepirates.api.dto.ProductDeliveryInfo;
import com.example.thepirates.api.dto.ProductOptionInfo;
import com.example.thepirates.api.dto.ProductSupplierInfo;
import lombok.*;

import javax.validation.Valid;
import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCreateRequest {
    private String name;
    private String description;
    @Valid
    private ProductSupplierInfo supplier;
    private ProductDeliveryInfo delivery;
    private List<ProductOptionInfo> options;

    @Builder
    public ProductCreateRequest(String name, String description, LocalTime open, LocalTime close,
                                ProductDeliveryInfo delivery, List<ProductOptionInfo> options) {
        this.name = name;
        this.description = description;
        this.supplier = new ProductSupplierInfo(open, close);
        this.delivery = delivery;
        this.options = options;
    }
}

