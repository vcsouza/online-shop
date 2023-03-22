package com.sda.onlineshop.repository;

import com.sda.onlineshop.dto.ProductDto;
import com.sda.onlineshop.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ProductRepository extends JpaRepository <Product, Long>{


}
