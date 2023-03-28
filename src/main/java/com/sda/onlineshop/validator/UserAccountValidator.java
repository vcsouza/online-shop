package com.sda.onlineshop.validator;

import com.sda.onlineshop.dto.UserAccountDto;
import com.sda.onlineshop.entities.UserAccount;
import com.sda.onlineshop.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

@Component
public class UserAccountValidator {
    @Autowired
    private UserAccountRepository userAccountRepository;
    public void validate(UserAccountDto userAccountDto, BindingResult bindingResult){
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findByEmail(userAccountDto.getEmail());
        if(optionalUserAccount.isPresent()){
            FieldError fieldError = new FieldError("userAccountDto","email","This email is already in use!");
            bindingResult.addError(fieldError);
        }

    }
}
