package com.sda.onlineshop.service;

import com.sda.onlineshop.dto.ProductDto;
import com.sda.onlineshop.entities.Product;
import com.sda.onlineshop.mapper.ProductMapper;
import com.sda.onlineshop.repository.ProductRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ProductService {

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private ProductMapper productMapper;
        public void create (ProductDto productDto){
            Product product = productMapper.map(productDto);
            productRepository.save(product);


    }

    public List<ProductDto> getAllProductDtoList() {
            List<ProductDto> productDtoList = new ArrayList<>();
            List<Product> productList = productRepository.findAll();
            for(Product product:productList){
                ProductDto productDto = productMapper.map(product);

                productDtoList.add(productDto);
            }
            return productDtoList;
    }
}

