package com.example.thepirates.service.dto;

import com.example.thepirates.api.dto.ProductInfo;
import com.example.thepirates.api.dto.request.ProductCreateRequest;
import com.example.thepirates.api.dto.ProductOptionInfo;
import com.example.thepirates.api.dto.response.ProductDetailQueryResponse;
import com.example.thepirates.api.dto.response.ProductQueryResponse;
import com.example.thepirates.domain.DeliveryType;
import com.example.thepirates.domain.Product;
import com.example.thepirates.domain.ProductOption;
import com.example.thepirates.domain.ProductRepository;
import com.example.thepirates.infra.exception.IdNotFoundException;
import com.example.thepirates.service.dto.input.ProductOptionCreateInput;
import com.example.thepirates.service.dto.output.CreateProductOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductOptionService productOptionService;

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
    }

    @Transactional
    public CreateProductOutput createProduct(ProductCreateRequest request) {
        Product product = productRepository.save(
                Product.builder()
                        .name(request.getName())
                        .description(request.getDescription())
                        .deliveryFee(request.getDelivery().getPrice())
                        .deliveryType(DeliveryType.valueOf(request.getDelivery().getType().toUpperCase()))
                        .deliveryClosing(request.getDelivery().getClosing())
                        .supplierOpen(request.getSupplier().getOpen())
                        .supplierClose(request.getSupplier().getClose())
                        .build()
        );
        List<ProductOption> optionList =
                productOptionService.createProductOptions(new ProductOptionCreateInput(product, request.getOptions()));
        product.addOptions(optionList);
        return new CreateProductOutput(product.getId());
    }

    @Transactional(readOnly = true)
    public ProductQueryResponse queryProducts() {
        List<Product> products = productRepository.findAllWithOption();
        return new ProductQueryResponse(
                products.stream()
                        .map(
                                it -> ProductInfo.builder()
                                        .price(priceInfoFormatting(it.getLowestPrice()))
                                        .name(it.getName())
                                        .description(it.getDescription())
                                        .build()
                        ).collect(Collectors.toList())
        );
    }

    @Transactional(readOnly = true)
    public ProductDetailQueryResponse queryProductDetail(Long id) {
        Product product = productRepository.findProductWithOptionById(id)
                .orElseThrow(IdNotFoundException::new);
        return ProductDetailQueryResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .deliveryType(product.getDelivery().getType().name())
                .options(product.getProductOptions().stream()
                        .map(
                                it -> ProductOptionInfo.builder()
                                        .stock(it.getStock())
                                        .price(it.getPrice())
                                        .name(it.getName())
                                        .build()
                        ).collect(Collectors.toList())
                )
                .build();
    }

    @Transactional
    public void deleteProduct(Long id){
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    private String priceInfoFormatting(int price) {
        DecimalFormat format = new DecimalFormat("###,###");
        String target = format.format(price);
        return target + " ~ ";
    }
}
