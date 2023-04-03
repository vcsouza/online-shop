package com.sda.onlineshop.service;

import com.sda.onlineshop.dto.UserAccountDto;
import com.sda.onlineshop.entities.UserAccount;
import com.sda.onlineshop.mapper.UserAccountMapper;
import com.sda.onlineshop.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private UserAccountRepository userAccountRepository;
    public void registerUser(UserAccountDto userAccountDto){
        UserAccount userAccount = userAccountMapper.map(userAccountDto);
        userAccountRepository.save(userAccount);

    }
}
