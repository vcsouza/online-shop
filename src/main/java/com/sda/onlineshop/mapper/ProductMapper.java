package com.sda.onlineshop.mapper;

import com.sda.onlineshop.dto.ProductDto;
import com.sda.onlineshop.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product map(ProductDto productDto){
        return Product.builder()
                .price(Double.parseDouble(productDto.getPrice()))
                .description(productDto.getDescription())
                .name(productDto.getName())
                .category(productDto.getCategory())
                .unitsInStock(Integer.valueOf(productDto.getStock()))
                .build();

    }
}
