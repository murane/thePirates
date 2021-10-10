package com.example.thepirates.integrationTest;

import com.example.thepirates.api.dto.request.ProductCreateRequest;
import com.example.thepirates.api.dto.response.ProductCreateResponse;
import com.example.thepirates.util.ProductData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;

@Sql("/truncate.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReceivingDateIntegrationTest {

    @Autowired
    private ProductData productData;

    @LocalServerPort
    private int port;

    @Test
    void 날짜반환(){
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
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .log().all()
        .when()
                .get("/rcvDate")
        .then()
                .log().all()
                .statusCode(200);
    }
}
