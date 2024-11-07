package com.alten.management.product.api;


import com.alten.management.product.model.Product;
import com.alten.management.product.model.ProductDto;
import com.alten.management.product.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;
    @Test
    void postProduct(@Autowired MockMvc mockMvc) throws Exception{
        ProductDto.ProductDtoBuilder productBuilder = ProductDto
                .builder();
        productBuilder.name("name");
        productBuilder.code("code");
        ProductDto productDto = productBuilder
                .build();
        mockMvc.perform(
                        post("/api/products")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productDto)))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    void findAllProducts(@Autowired MockMvc mockMvc) throws Exception{
        productRepository.deleteAll();
        productRepository.save(returnProduct("name0",14,12L));
        productRepository.save(returnProduct("name1",11,10L));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$[0].name").value("name0"));
    }

    @Test
    void findProductById(@Autowired MockMvc mockMvc) throws Exception{
        productRepository.deleteAll();
        productRepository.save(returnProduct("name0",14,10L));

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("name").value("name0"));
    }

    private Product returnProduct(String name, long quantity, long price){
        Product product = new Product();
        product.setName( name);
        product.setQuantity(quantity);
        product.setPrice(price);
        return product;
    }

}
