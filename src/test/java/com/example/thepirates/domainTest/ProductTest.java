package com.example.thepirates.domainTest;

import com.example.thepirates.domain.Product;
import com.example.thepirates.domain.ProductOption;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ProductTest {
    @Test
    void 가장낮은가격테스트(){
        Product product = Product.builder()
                .name("testName")
                .description("testdesc")
                .build();
        ProductOption opt1 = ProductOption.builder()
                .product(product)
                .name("option1")
                .price(500)
                .stock(100)
                .build();
        ProductOption opt2 = ProductOption.builder()
                .product(product)
                .name("option2")
                .price(200)
                .stock(100)
                .build();
        ProductOption opt3 = ProductOption.builder()
                .product(product)
                .name("option3")
                .price(400)
                .stock(100)
                .build();
        product.addOptions(List.of(opt1,opt2,opt3));
        var result = product.getLowestPrice();
        Assertions.assertThat(result).isEqualTo(200);
    }
}
