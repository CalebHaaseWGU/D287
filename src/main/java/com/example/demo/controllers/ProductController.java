package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Handle the "Buy Now" action
    @GetMapping("/buyProduct")
    public String buyProduct(@RequestParam("productID") Long productId, RedirectAttributes redirectAttributes) {
        // Convert Long to int
        int productIdInt = productId.intValue();

        // Find the product by its ID
        Product product = productService.findById(productIdInt);

        // Check if there is enough inventory to decrement
        if (product.getInv() > 0) {
            product.setInv(product.getInv() - 1);
            productService.save(product);
            redirectAttributes.addFlashAttribute("message", "Purchase successful!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Purchase failed: Out of stock.");
        }

        // Redirect back to the main screen with feedback messages
        return "redirect:/mainscreen";
    }
}



