package com.sda.onlineshop.service;

import com.sda.onlineshop.dto.CartEntryDto;
import com.sda.onlineshop.dto.CheckoutDto;
import com.sda.onlineshop.dto.ProductDto;
import com.sda.onlineshop.dto.ProductQuantityDto;
import com.sda.onlineshop.entities.Cart;
import com.sda.onlineshop.entities.CartEntry;
import com.sda.onlineshop.entities.Product;
import com.sda.onlineshop.mapper.CartEntryMapper;
import com.sda.onlineshop.repository.CartEntryRepository;
import com.sda.onlineshop.repository.CartRepository;
import com.sda.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartEntryRepository cartEntryRepository;
    @Autowired
    private CartEntryMapper cartEntryMapper;
    public void addToCart (String productId, ProductQuantityDto productQuantityDto,String loggedInUserEmail){
        CartEntry cartEntry = new CartEntry();

        Cart cart = cartRepository.findByUserAccountEmail(loggedInUserEmail);
        cartEntry.setCart(cart);

        Optional<Product> optionalProduct = productRepository.findById(Long.valueOf(productId));
        if(optionalProduct.isEmpty()){
            throw new RuntimeException("Product ID is not valid");
        }
        Product product = optionalProduct.get();
        cartEntry.setProduct(product);

        cartEntry.setQuantity(Integer.valueOf(productQuantityDto.getQuantity()));

        cartEntryRepository.save(cartEntry);
    }

    public CheckoutDto getCheckoutDtoByUserEmail(String userEmail) {
        Cart cart = cartRepository.findByUserAccountEmail(userEmail);
        List<CartEntryDto> cartEntryDtoList = new ArrayList<>();
        Double subtotal = 0.00;
        for (CartEntry cartEntry:cart.getCartEntryList()){
            CartEntryDto cartEntryDto = cartEntryMapper.map(cartEntry);
            cartEntryDtoList.add(cartEntryDto);
            subtotal += cartEntry.getProduct().getPrice()*cartEntry.getQuantity();
        }
        return CheckoutDto.builder()
                .cartEntryDtoList(cartEntryDtoList)
                .subtotal(String.valueOf(subtotal))
                .shippingFee("30")
                .total(String.valueOf(subtotal+30))
                .build();
    }
}
