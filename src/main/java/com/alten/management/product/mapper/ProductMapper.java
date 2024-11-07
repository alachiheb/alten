package com.alten.management.product.mapper;



import com.alten.management.product.model.Product;
import com.alten.management.product.model.ProductDto;
import com.alten.management.product.model.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductDto productDto) ;
    ProductResponse toProductResponse(Product product) ;
}

