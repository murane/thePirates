package com.example.thepirates.service.dto;

import com.example.thepirates.api.dto.ProductOptionInfo;
import com.example.thepirates.domain.Product;
import com.example.thepirates.domain.ProductOption;
import com.example.thepirates.domain.ProductOptionRepository;
import com.example.thepirates.service.dto.input.ProductOptionCreateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductOptionService {
    private final ProductOptionRepository productOptionRepository;

    @Transactional
    public List<ProductOption> createProductOptions(ProductOptionCreateInput input) {
        return input.getOptionInfos().stream()
                .map(
                        it -> createProductOption(it, input.getProduct())
                )
                .collect(Collectors.toList());
    }

    private ProductOption createProductOption(ProductOptionInfo productOptionInfo, Product product) {
        return productOptionRepository.save(
                ProductOption.builder()
                        .product(product)
                        .name(productOptionInfo.getName())
                        .stock(productOptionInfo.getStock())
                        .price(productOptionInfo.getPrice())
                        .build()
        );
    }
}
