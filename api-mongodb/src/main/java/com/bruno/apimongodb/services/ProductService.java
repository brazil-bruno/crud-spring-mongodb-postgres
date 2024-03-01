package com.bruno.apimongodb.services;

import com.bruno.apimongodb.entities.Product;
import com.bruno.apimongodb.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product newProduct(Product productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        return productRepository.save(product);
    }

}
