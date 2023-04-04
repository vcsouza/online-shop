package com.sda.onlineshop.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CheckoutDto {
    private String subtotal;
    private String shippingFee;
    private String total;
    private List<CartEntryDto> cartEntryDtoList;
}
