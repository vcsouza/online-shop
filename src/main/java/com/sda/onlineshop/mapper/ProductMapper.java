package com.sda.onlineshop.mapper;
import org.apache.tomcat.util.codec.binary.Base64;
import com.sda.onlineshop.dto.ProductDto;
import com.sda.onlineshop.entities.Product;
import lombok.Builder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@Builder
public class ProductMapper {
    //return a product where the parameter is a Dto coming with info from HTML
    public Product map(ProductDto productDto, MultipartFile productImg){
        return Product.builder()
                .price(Double.parseDouble(productDto.getPrice()))
                .description(productDto.getDescription())
                .name(productDto.getName())
                .category(productDto.getCategory())
                .unitsInStock(Integer.valueOf(productDto.getUnitsInStock()))
                .img(convertToByte(productImg))
                .build();
    //it's a constructor
    }
    public ProductDto map (Product product){
        return ProductDto.builder()
                .id(String.valueOf(product.getId()))
                .price(String.valueOf(product.getPrice()))
                .description(product.getDescription())
                .name(product.getName())
                .category(product.getCategory())
                .unitsInStock(String.valueOf(product.getUnitsInStock()))
                .img(Base64.encodeBase64String(product.getImg()))
                .build();
    }
    private byte[] convertToByte (MultipartFile productImg){
        try {
            return productImg.getBytes();
        } catch (IOException e) {
            return new byte[0];
        }
    }
}
