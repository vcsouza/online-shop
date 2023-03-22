package com.sda.onlineshop.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String price;
    private String description;
    private String category;
    private String stock;

}
