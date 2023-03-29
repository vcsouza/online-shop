package com.sda.onlineshop.mapper;

import com.sda.onlineshop.dto.UserAccountDto;
import com.sda.onlineshop.entities.UserAccount;
import com.sda.onlineshop.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAccountMapper {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserAccount map(UserAccountDto userAccountDto){
        return UserAccount.builder()
                .email(userAccountDto.getEmail())
                .address(userAccountDto.getAddress())
                .name(userAccountDto.getName())
                .password(bCryptPasswordEncoder.encode(userAccountDto.getPassword()))
                .userRole(UserRole.valueOf(userAccountDto.getUserRole()))
                .build();
    }
}
