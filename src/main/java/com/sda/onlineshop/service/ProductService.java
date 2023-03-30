package com.sda.onlineshop.service;

import com.sda.onlineshop.dto.ProductDto;
import com.sda.onlineshop.entities.Product;
import com.sda.onlineshop.mapper.ProductMapper;
import com.sda.onlineshop.repository.ProductRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ProductService {
    // the methods implemented onn Service will be used on Controller

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private ProductMapper productMapper;

        //we receive a productDto from HTML
        //we build a product from productDto with the help of the method map from class ProductMapper
        // with productRepository's help we save the product in the DB
        public void create (ProductDto productDto, MultipartFile productImg){
            Product product = productMapper.map(productDto, productImg);
            productRepository.save(product);


    }
    //used to show all the products on the home page

    public List<ProductDto> getAllProductDtoList() {
            //instance a list
        List<ProductDto> productDtoList = new ArrayList<>();
        //instance a list of type Product
        // with the function findAll we take all products from the DB
        List<Product> productList = productRepository.findAll();
        //iterate through all products and create a list of productDtos to be displayed on the HTML
        for (Product product : productList) {
            ProductDto productDto = productMapper.map(product);

            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    public Optional<ProductDto> getProductDtoById (String id){
        Optional <Product> optionalProduct = productRepository.findById(Long.valueOf(id));
        if(optionalProduct.isEmpty()){
            return Optional.empty();
        }
        ProductDto productDto = productMapper.map(optionalProduct.get());
        return Optional.of(productDto);
    }
}

