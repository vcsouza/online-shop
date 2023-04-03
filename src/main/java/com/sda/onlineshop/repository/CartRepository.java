package com.sda.onlineshop.repository;

import com.sda.onlineshop.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository <Cart, Long>{
    Cart findByUserAccountEmail(String email);
}
