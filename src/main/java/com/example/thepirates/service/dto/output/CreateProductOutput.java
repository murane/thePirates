package com.example.thepirates.service.dto.output;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateProductOutput {
    private Long productId;

    public CreateProductOutput(Long productId) {
        this.productId = productId;
    }
}
