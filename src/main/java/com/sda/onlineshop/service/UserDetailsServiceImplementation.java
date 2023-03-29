package com.sda.onlineshop.service;

import com.sda.onlineshop.entities.UserAccount;
import com.sda.onlineshop.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //search for an user
        System.out.println("I'm trying to login with the email address: "+email);
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findByEmail(email);
        if (optionalUserAccount.isEmpty()){
            throw new UsernameNotFoundException(email);
        }
        UserAccount userAccount = optionalUserAccount.get();
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userAccount.getUserRole().name()));

        return new User(email, userAccount.getPassword(), roles);
    }
}
