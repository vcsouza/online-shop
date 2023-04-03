package com.sda.onlineshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
    //middleman between html and java
    //data transfer object
    //send the fields empty to HTML and returns them populated (collecting data from HTML)
public class ProductDto {
    private String id;
    private String name;
    private String price;
    private String description;
    private String category;
    private String unitsInStock;
    private String img;

}
