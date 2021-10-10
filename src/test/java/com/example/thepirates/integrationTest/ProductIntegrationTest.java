package com.example.thepirates.integrationTest;

import com.example.thepirates.api.dto.request.ProductCreateRequest;
import com.example.thepirates.api.dto.ProductDeliveryInfo;
import com.example.thepirates.api.dto.ProductOptionInfo;
import com.example.thepirates.api.dto.response.ProductCreateResponse;
import com.example.thepirates.api.dto.response.ProductDetailQueryResponse;
import com.example.thepirates.api.dto.response.ProductQueryResponse;
import com.example.thepirates.domain.DeliveryType;
import com.example.thepirates.util.DBClean;
import com.example.thepirates.util.ProductData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalTime;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Sql("/truncate.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private DBClean dbClean;

    @Autowired
    private ProductData productData;

    @Disabled
    //@AfterEach
    void cleanUp(){
        dbClean.execute();
    }

    @Test
    void 상품추가(){
        ProductCreateRequest request = productData.연어생성요청();
        given()
                .port(port)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .log().all()
        .when()
                .post("/product")
        .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    void 상품목록조회(){
        ProductCreateRequest request1 = productData.연어생성요청();
        given()
                .port(port)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request1)
        .when()
                .post("/product")
        .then()
                .statusCode(201);

        ProductCreateRequest request2 = productData.전복생성요청();
        given()
                .port(port)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request2)
        .when()
                .post("/product")
        .then()
                .statusCode(201);

        var body = given()
                .port(port)
                .log().all()
        .when()
                .get("/products")
        .then()
                .log().all()
                .statusCode(200)
                .extract().as(ProductQueryResponse.class);
    }

    @Test
    void 상품상세조회(){
        ProductCreateRequest request = productData.연어생성요청();
        var response = given()
                .port(port)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
        .when()
                .post("/product")
        .then()
                .extract().as(ProductCreateResponse.class);

        Long id = response.getId();

        var body = given()
                .port(port)
                .param("id",id)
                .log().all()
        .when()
                .get("/product")
        .then()
                .log().all()
                .statusCode(200)
                .extract().as(ProductDetailQueryResponse.class);

        assertThat(body.getName()).isEqualTo(request.getName());
        assertThat(body.getDescription()).isEqualTo(request.getDescription());
        assertThat(body.getDeliveryType()).isEqualTo(request.getDelivery().getType());
        assertThat(body.getOptions()).containsAll(request.getOptions());
    }

    @Test
    void 상품삭제(){
        ProductCreateRequest request = productData.연어생성요청();
        var response = given()
                .port(port)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
        .when()
                .post("/product")
        .then()
                .extract().as(ProductCreateResponse.class);

        Long id = response.getId();

        given()
                .port(port)
                .param("id",id)
                .log().all()
        .when()
                .delete("/product")
        .then()
                .log().all()
                .statusCode(204);
    }
}
