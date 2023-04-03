package com.sda.onlineshop.service;

import com.sda.onlineshop.dto.ProductDto;
import com.sda.onlineshop.dto.ProductQuantityDto;
import com.sda.onlineshop.entities.Cart;
import com.sda.onlineshop.entities.CartEntry;
import com.sda.onlineshop.entities.Product;
import com.sda.onlineshop.repository.CartEntryRepository;
import com.sda.onlineshop.repository.CartRepository;
import com.sda.onlineshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartEntryRepository cartEntryRepository;
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
}
