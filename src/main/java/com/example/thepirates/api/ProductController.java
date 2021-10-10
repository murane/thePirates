package com.example.thepirates.api;

import com.example.thepirates.api.dto.request.ProductCreateRequest;
import com.example.thepirates.api.dto.response.ProductCreateResponse;
import com.example.thepirates.api.dto.response.ProductDetailQueryResponse;
import com.example.thepirates.api.dto.response.ProductQueryResponse;
import com.example.thepirates.service.dto.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.validation.Valid;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<ProductCreateResponse> createProduct(@Valid @RequestBody ProductCreateRequest request) {
        var output = productService.createProduct(request);
        UriComponents uriComponents = MvcUriComponentsBuilder
                .fromMethodCall(on(ProductController.class).createProduct(request))
                .build();
        return ResponseEntity
                .created(uriComponents.toUri())
                .body(new ProductCreateResponse(output.getProductId()));
    }

    @GetMapping("/products")
    public ResponseEntity<ProductQueryResponse> queryProducts(){
        var output = productService.queryProducts();
        return ResponseEntity.ok(output);
    }

    @GetMapping("/product")
    public ResponseEntity<ProductDetailQueryResponse>
        queryProductDetail(@RequestParam(name= "id") Long id){
        var output = productService.queryProductDetail(id);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/product")
    public ResponseEntity<Void> deleteProduct(@RequestParam(name = "id") Long id){
        productService.deleteProduct(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
