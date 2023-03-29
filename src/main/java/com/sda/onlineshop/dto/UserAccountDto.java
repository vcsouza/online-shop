package com.sda.onlineshop.dto;

import lombok.Data;

@Data
public class UserAccountDto {
    private String name;
    private String address;
    private String email;
    private String password;
    private String userRole;

}
