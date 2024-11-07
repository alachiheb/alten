package com.alten.management.product.service;


import com.alten.management.product.exception.ProductExceptionMessage;
import com.alten.management.product.exception.ProductNotFoundException;
import com.alten.management.product.model.Product;
import com.alten.management.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest
{
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;
    @Test
    void postProductOk()  {
        Product product1 = returnProduct("name0",14,10L);
        when(productRepository.save(any())).thenReturn(product1);
        Product productResponse = productService.addProduct(product1);
        assertEquals(productResponse.getName(), product1.getName());
    }

    @Test
    void findAllProductsOk()  {
        Product product1 = returnProduct("name0",14,10L);
        Product product2 = returnProduct("name1",12,11L);
        when(productRepository.findAll()).thenReturn(List.of(product1,product2));
        List<Product> ProductResponseList = productService.getAllProducts();
        assertEquals(ProductResponseList.size(), 2);
    }

    @Test
    void findProductByIdOK()  {
        Product product1 = returnProduct("name0",14,10L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        Product productReturn = productService.getProductById(1L);
        assertEquals(productReturn.getName(), product1.getName());
    }

    @Test
    void whenDerivedExceptionThrown_thenAssertionSucceeds() {
        productRepository.deleteAll();
        Exception exception = assertThrows(ProductNotFoundException.class, () -> productService.getProductById(11L));
        assertTrue(exception.getMessage().contains(ProductExceptionMessage.PRODUCT_NOT_FOUND.toErrorMessage()));
    }

    private Product returnProduct(String name, long quantity, long price){
        Product product = new Product();
        product.setName( name);
        product.setQuantity(quantity);
        product.setPrice(price);
        return product;
    }


}
