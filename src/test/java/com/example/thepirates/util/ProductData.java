package com.example.thepirates.util;

import com.example.thepirates.api.dto.ProductDeliveryInfo;
import com.example.thepirates.api.dto.ProductOptionInfo;
import com.example.thepirates.api.dto.request.ProductCreateRequest;
import com.example.thepirates.domain.DeliveryType;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class ProductData {
    public ProductCreateRequest 연어생성요청(){
        return ProductCreateRequest.builder()
                .name("노르웨이산 연어")
                .description("노르웨이산 연어 300g, 500g, 반마리 필렛")
                .delivery(
                        ProductDeliveryInfo.builder()
                                .type(DeliveryType.FAST.name())
                                .closing(LocalTime.of(12, 0))
                                .price(10000)
                                .build()
                )
                .open(LocalTime.of(10, 0))
                .close(LocalTime.of(20, 0))
                .options(List.of(
                        ProductOptionInfo.builder()
                                .name("생연어 몸통살 300g")
                                .stock(99)
                                .price(10000)
                                .build(),
                        ProductOptionInfo.builder()
                                .name("생연어 몸통살 500g")
                                .stock(99)
                                .price(17000)
                                .build()
                ))
                .build();
    }
    public ProductCreateRequest 전복생성요청(){
        return ProductCreateRequest.builder()
                .name("완도 전복")
                .description("산지직송 완도 전복 1kg (7미~60미)")
                .delivery(
                        ProductDeliveryInfo.builder()
                                .type(DeliveryType.REGULAR.name())
                                .closing(LocalTime.of(18, 0))
                                .price(10000)
                                .build()
                )
                .open(LocalTime.of(6, 0))
                .close(LocalTime.of(20, 0))
                .options(List.of(
                        ProductOptionInfo.builder()
                                .name("대 7~8미")
                                .stock(99)
                                .price(50000)
                                .build(),
                        ProductOptionInfo.builder()
                                .name("중 14~15미")
                                .stock(99)
                                .price(34000)
                                .build()
                ))
                .build();
    }
}
