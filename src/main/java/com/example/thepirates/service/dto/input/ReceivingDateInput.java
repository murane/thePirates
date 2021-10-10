package com.example.thepirates.service.dto.input;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReceivingDateInput {
    private Long productId;

    public ReceivingDateInput(Long productId) {
        this.productId = productId;
    }
}
