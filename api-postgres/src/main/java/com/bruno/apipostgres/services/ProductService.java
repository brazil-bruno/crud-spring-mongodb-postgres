package com.bruno.apipostgres.services;

import com.bruno.apipostgres.entities.Product;
import com.bruno.apipostgres.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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
                ._id(UUID.randomUUID().toString())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Product findProductById(String id) {
        Product product = productRepository.findById(id).get();
        return product;
    }

    @Transactional
    public Product updateProduct(String id, Product object) {
        Product entity = productRepository.findById(id).get();
        copyObjectToEntity(object, entity);
        return productRepository.save(entity);
    }

    private void copyObjectToEntity(Product object, Product entity) {
        entity.setName(object.getName());
        entity.setDescription(object.getDescription());
        entity.setDescription(object.getDescription());
        entity.setPrice(object.getPrice());
    }
}
