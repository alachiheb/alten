package com.alten.management.product.api;


import com.alten.management.product.mapper.ProductMapper;
import com.alten.management.product.model.Product;
import com.alten.management.product.model.ProductDto;
import com.alten.management.product.model.ProductResponse;
import com.alten.management.product.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public record ProductController(ProductService productService, ProductMapper productMapper) {

    @GetMapping("/products")
    public ResponseEntity<List<Product>>  findAllProducts() {
        List<Product> sortedList = productService.getAllProducts().stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(sortedList);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponse>  saveDeliverySlot(@Valid @RequestBody ProductDto productDto) {
        Product product = productService.addProduct(productMapper.toProduct(productDto));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productMapper.toProductResponse(product));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product>  findProductById(@PathVariable @NotNull Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateTutorial(@PathVariable("id") long id, @RequestBody Product product) {
        productService.update(product,id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        productService.removeProductById(id);
        return ResponseEntity.ok().build();
    }

}
