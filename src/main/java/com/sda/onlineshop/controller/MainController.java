package com.sda.onlineshop.controller;


import com.sda.onlineshop.dto.ProductDto;
import com.sda.onlineshop.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class MainController {

    @Autowired
    private ProductService productService;

    @GetMapping("/addProduct")
    public String addProductGet(Model model) {
        ProductDto productDto = new ProductDto();

        model.addAttribute("productDto", productDto);

        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProductPost(@ModelAttribute ProductDto productDto, @RequestParam("productImg") MultipartFile productImg) {
        System.out.println(productDto);
        log.info("apelat add product");
        productService.create(productDto,productImg);
        return "redirect:/addProduct";
    }

    @GetMapping("/home")
    public String homeGet(Model model) {

    List<ProductDto> productDtoList = productService.getAllProductDtoList();
    model.addAttribute("productDtoList",productDtoList);
        System.out.println(productDtoList);
    return "home";

    }
    @GetMapping("/product/{id}")
    public String viewProductGet (Model model, @PathVariable(value = "id") String id){
        System.out.println("I clicked the product with id: "+ id);
        Optional<ProductDto> optionalProductDto= productService.getProductDtoById(id);
        if (optionalProductDto.isEmpty()){
            return "error";
        }
        model.addAttribute("productDto",optionalProductDto.get());
        return "viewProduct";
    }
    @GetMapping("/register")
    public String registerGet(){
        return "register";
    }
    @GetMapping("/login")
    public String loginGet(){
        return "login";
    }
}