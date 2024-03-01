package com.bruno.apipostgres.controllers;

import com.bruno.apipostgres.entities.Product;
import com.bruno.apipostgres.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findAllProducts() {
        return productService.findAllProducts();
    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Product newProduct(@RequestBody Product productRequest) {
        return productService.newProduct(productRequest);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable(name = "id") String _id) {
        productService.deleteProduct(_id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product findProductById(@PathVariable(name = "id") String id) {
        return productService.findProductById(id);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@PathVariable(name = "id") String _id, @RequestBody Product product) {
        return productService.updateProduct(_id, product);
    }
}
