package com.alten.management.product.service;


import com.alten.management.product.exception.ProductExceptionMessage;
import com.alten.management.product.exception.ProductNotFoundException;
import com.alten.management.product.model.Product;
import com.alten.management.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public record ProductService(ProductRepository productRepository){

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product addProduct(Product product){
        //TODO
        // test if slot is available
        return productRepository.save(product);
    }

    public Product getProductById(long id){
        if (productRepository.findById(id).isEmpty()){
            throw new ProductNotFoundException(ProductExceptionMessage.PRODUCT_NOT_FOUND.toErrorMessage());
        }
        return productRepository.findById(id).get();

    }

    public void update(Product product, long id) {
        productRepository
                .findById(id) // returns Optional<User>
                .ifPresent(product1 -> {
                    product1.setName(product.getName());
                    product1.setPrice(product.getPrice());
                    product1.setQuantity(product.getQuantity());
                    productRepository.save(product1);
                });
    }

    public void removeProductById(long id){
        if (productRepository.findById(id).isEmpty()){
            throw new ProductNotFoundException(ProductExceptionMessage.PRODUCT_NOT_FOUND.toErrorMessage());
        }
         productRepository.deleteById(id);
    }


}
