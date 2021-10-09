package com.example.thepirates.service.dto;

import com.example.thepirates.api.dto.request.ProductCreateRequest;
import com.example.thepirates.domain.Product;
import com.example.thepirates.domain.ProductOption;
import com.example.thepirates.domain.ProductRepository;
import com.example.thepirates.infra.exception.IdNotFoundException;
import com.example.thepirates.service.dto.input.ProductOptionCreateInput;
import com.example.thepirates.service.dto.output.CreateProductOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductOptionService productOptionService;

    @Transactional(readOnly = true)
    public Product getProductById(Long id){
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
                        .deliveryType(request.getDelivery().getType())
                        .deliveryClosing(request.getDelivery().getClosing())
                        .build()
        );
        List<ProductOption> optionList =
                productOptionService.createProductOptions(new ProductOptionCreateInput(product, request.getOptions()));
        product.addOptions(optionList);
        return new CreateProductOutput(product.getId());
    }
}
