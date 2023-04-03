package com.sda.onlineshop.repository;

import com.sda.onlineshop.entities.CartEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartEntryRepository extends JpaRepository <CartEntry, Long>{
}
