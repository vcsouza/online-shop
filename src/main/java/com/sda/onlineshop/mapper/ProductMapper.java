package com.sda.onlineshop.mapper;

import com.sda.onlineshop.dto.ProductDto;
import com.sda.onlineshop.entities.Product;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class ProductMapper {
    public Product map(ProductDto productDto){
        return Product.builder()
                .price(Double.parseDouble(productDto.getPrice()))
                .description(productDto.getDescription())
                .name(productDto.getName())
                .category(productDto.getCategory())
                .unitsInStock(Integer.valueOf(productDto.getUnitsInStock()))
                .build();

    }
    public ProductDto map (Product product){
        return ProductDto.builder()
                .id(String.valueOf(product.getId()))
                .price(String.valueOf(product.getPrice()))
                .description(product.getDescription())
                .name(product.getName())
                .category(product.getCategory())
                .unitsInStock(String.valueOf(product.getUnitsInStock()))
                .build();
    }
}
