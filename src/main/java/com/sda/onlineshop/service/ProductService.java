package com.sda.onlineshop.service;

import com.sda.onlineshop.dto.ProductDto;
import com.sda.onlineshop.entities.Product;
import com.sda.onlineshop.mapper.ProductMapper;
import com.sda.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

