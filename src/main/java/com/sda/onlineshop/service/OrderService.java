package com.sda.onlineshop.service;

import com.sda.onlineshop.entities.Cart;
import com.sda.onlineshop.entities.CartEntry;
import com.sda.onlineshop.entities.Order;
import com.sda.onlineshop.repository.CartEntryRepository;
import com.sda.onlineshop.repository.CartRepository;
import com.sda.onlineshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartEntryRepository cartEntryRepository;
    public void placeOrder(String loggedInUserEmail){
        Cart cart = cartRepository.findByUserAccountEmail(loggedInUserEmail);
        Order order = new Order();
        order.setUserAccount(cart.getUserAccount());
        //order.setCartEntryList(cart.getCartEntryList());
        orderRepository.save(order);
        for(CartEntry cartEntry: cart.getCartEntryList()){
            cartEntry.setCart(null);
            cartEntry.setOrder(order);
            cartEntryRepository.save(cartEntry);
        }
    }
}
