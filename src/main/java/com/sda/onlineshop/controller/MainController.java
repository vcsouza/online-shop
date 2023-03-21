package com.sda.onlineshop.controller;


import com.sda.onlineshop.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MainController {
    @GetMapping("/addProduct")
    public String addProductsGet(Model model){
        ProductDto productDto = new ProductDto();
        productDto.setName("Laptop");
        model.addAttribute("productDto",productDto);

        return "addProduct";
    }
    @PostMapping("/addProduct")
    public String addProductsPost(){
        log.info("addProduct returned");
        return "redirect:/addProduct";
    }
}
