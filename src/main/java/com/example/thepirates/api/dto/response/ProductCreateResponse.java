package com.example.thepirates.api.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCreateResponse {
    private Long id;

    public ProductCreateResponse(Long id) {
        this.id = id;
    }
}
