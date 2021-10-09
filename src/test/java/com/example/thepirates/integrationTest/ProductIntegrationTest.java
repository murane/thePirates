package com.example.thepirates.integrationTest;

import com.example.thepirates.api.dto.request.ProductCreateRequest;
import com.example.thepirates.api.dto.request.ProductDeliveryInfo;
import com.example.thepirates.api.dto.request.ProductOptionInfo;
import com.example.thepirates.domain.DeliveryType;
import com.example.thepirates.util.DBClean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import java.awt.*;
import java.time.LocalTime;
import java.util.List;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private DBClean dbClean;

    @AfterEach
    void cleanUp(){
        dbClean.execute();
    }

    @Test
    void 상품추가(){
        ProductCreateRequest request = ProductCreateRequest.builder()
                .name("노르웨이산 연어")
                .description("노르웨이산 연어 300g, 500g, 반마리 필렛")
                .delivery(
                        ProductDeliveryInfo.builder()
                                .type(DeliveryType.FAST)
                                .closing(LocalTime.of(12, 0))
                                .price(10000)
                                .build()
                )
                .open(LocalTime.of(20, 0))
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
        given()
                .port(port)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .log().all()
        .when()
                .post("/product")
        .then()
                .log().all()
                .statusCode(404);
    }

    void 상품목록조회(){

    }

    void 상품상세조회(){

    }

    void 상품삭제(){

    }
}
