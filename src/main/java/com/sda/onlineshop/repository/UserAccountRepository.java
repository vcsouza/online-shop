package com.sda.onlineshop.repository;

import com.sda.onlineshop.entities.Product;
import com.sda.onlineshop.entities.UserAccount;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository  extends JpaRepository<UserAccount, Long> {
    public Optional<UserAccount> findByEmail(String email);


}
