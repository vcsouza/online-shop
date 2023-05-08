package com.sda.onlineshop.controller;


import com.sda.onlineshop.dto.*;
import com.sda.onlineshop.entities.Cart;
import com.sda.onlineshop.service.CartService;
import com.sda.onlineshop.service.OrderService;
import com.sda.onlineshop.service.ProductService;
import com.sda.onlineshop.service.UserAccountService;
import com.sda.onlineshop.validator.UserAccountValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class MainController {
    //wait for the website requests and send them on the correct way
    //basically a door-man
    //every method on Controller needs to bea  String

    //autowired -> to use a class
    //object is the instance of a class
    @Autowired
    private ProductService productService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserAccountValidator userAccountValidator;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    //send the dto to comeback with data
    @GetMapping("/addProduct")
    public String addProductGet(Model model) {
        ProductDto productDto = new ProductDto();

        model.addAttribute("productDto", productDto);

        return "addProduct";
    }
    //sends data to DB
    @PostMapping("/addProduct")
    public String addProductPost(@ModelAttribute ProductDto productDto, @RequestParam("productImg") MultipartFile productImg) {
        //System.out.println(productDto);
        //log.info("apelat add product");
        productService.create(productDto,productImg);
        return "redirect:/addProduct";
    }

    @GetMapping("/home")
    public String homeGet(Model model, Authentication authentication) {

    List<ProductDto> productDtoList = productService.getAllProductDtoList();
    model.addAttribute("productDtoList",productDtoList);
        System.out.println(productDtoList);

    int cartQuantity = cartService.getQuantity(authentication.getName());
    model.addAttribute("quantity",cartQuantity);
    return "home";

    }
    @GetMapping("/product/{id}")

    public String viewProductGet (Model model,Authentication authentication, @PathVariable(value = "id") String id){
        //System.out.println("I clicked the product with id: "+ id);
        Optional<ProductDto> optionalProductDto= productService.getProductDtoById(id);
        if (optionalProductDto.isEmpty()){
            return "error";
        }
        model.addAttribute("productDto",optionalProductDto.get());
        ProductQuantityDto productQuantityDto = new ProductQuantityDto();
        model.addAttribute("productQuantityDto",productQuantityDto);

        int cartQuantity = cartService.getQuantity(authentication.getName());
        model.addAttribute("quantity",cartQuantity);

        return "viewProduct";
    }
    @PostMapping("/product/{id}")
    public String addToCartPost(@ModelAttribute ProductQuantityDto productQuantityDto,
                                @PathVariable(value = "id") String id, Authentication authentication){
//        System.out.println(productQuantityDto);
//        System.out.println("I've added to the cart: " +id);
        System.out.println(authentication.getName());
        cartService.addToCart(id,productQuantityDto, authentication.getName());
        return "redirect:/product/"+id;

    }
    @GetMapping("/register")
    public String registerGet(Model model){
        UserAccountDto userAccountDto = new UserAccountDto();
        model.addAttribute("userAccountDto",userAccountDto);
        return "register";
    }
    @PostMapping("/register")
    public String registerPost (@ModelAttribute UserAccountDto userAccountDto, BindingResult bindingResult){
        userAccountValidator.validate(userAccountDto, bindingResult);
        if(bindingResult.hasErrors()){
            return "register";
        }
        userAccountService.registerUser(userAccountDto);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String loginGet(){
        return "login";
    }

    @GetMapping("/checkout")
    public String checkoutGet(Model model, Authentication authentication) {
        //hard coded test
//        CheckoutDto checkoutDto = CheckoutDto.builder()
//                .total("455")
//                .shippingFee("9")
//                .subtotal("446")
//                .productCheckoutDtoList(Arrays.asList(
//                        ProductCheckoutDto.builder()
//                                .name("prune")
//                                .quantity("23")
//                                .price("0.3")
//                                .total("6.9")
//                                .build(),
//                        ProductCheckoutDto.builder()
//                                .name("mere")
//                                .quantity("30")
//                                .price("0.2")
//                                .total("6.0")
//                                .build()
//                ))
//            .build();
        CheckoutDto checkoutDto = cartService.getCheckoutDtoByUserEmail(authentication.getName());
        model.addAttribute("checkoutDto",checkoutDto);
        return "checkout";
    }
    @GetMapping("/confirmation")
    public String confirmationGet(){
        return "error";
    }
    @PostMapping("/confirmation")
    public String confirmationPost(Model model, Authentication authentication){
        orderService.placeOrder(authentication.getName());
        CheckoutDto checkoutDto = cartService.getCheckoutDtoByUserEmail(authentication.getName());
        model.addAttribute("checkoutDto", checkoutDto);
        return "confirmation";
    }

}
