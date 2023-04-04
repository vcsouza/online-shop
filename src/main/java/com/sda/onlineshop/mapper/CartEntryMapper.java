package com.sda.onlineshop.mapper;

import com.sda.onlineshop.dto.CartEntryDto;
import com.sda.onlineshop.entities.CartEntry;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class CartEntryMapper {
    public CartEntryDto map (CartEntry cartEntry){
        return CartEntryDto.builder()
                .name(cartEntry.getProduct().getName())
                .price(String.valueOf(cartEntry.getProduct().getPrice()))
                .quantity(String.valueOf(cartEntry.getQuantity()))
                .total(String.valueOf(cartEntry.getProduct().getPrice()* cartEntry.getQuantity()))
                .image(Base64.encodeBase64String(cartEntry.getProduct().getImg()))
                .build();
    }

}
