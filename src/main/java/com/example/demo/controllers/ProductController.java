package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Handle the "Buy Now" action
    @GetMapping("/buyProduct")
    public String buyProduct(@RequestParam("productID") Long productId, Model model) {
        // Convert Long to int
        int productIdInt = productId.intValue();

        // Find the product by its ID
        Product product = productService.findById(productIdInt);

        // Check if there is enough inventory to decrement
        if (product.getInv() > 0) {
            product.setInv(product.getInv() - 1);
            productService.save(product);
            model.addAttribute("message", "Purchase successful!");
        } else {
            model.addAttribute("message", "Purchase failed: Out of stock.");
        }

        // Redirect back to the main screen
        return "redirect:/mainscreen";
    }

}

