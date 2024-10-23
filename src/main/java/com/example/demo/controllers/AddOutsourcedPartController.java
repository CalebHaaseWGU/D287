package com.example.demo.controllers;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Controller for handling Outsourced Part forms and validation
 */
@Controller
public class AddOutsourcedPartController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddOutPart")
    public String showFormAddOutsourcedPart(Model theModel) {
        OutsourcedPart outsourcedPart = new OutsourcedPart();
        theModel.addAttribute("outsourcedpart", outsourcedPart);
        return "OutsourcedPartForm";
    }

    @PostMapping("/showFormAddOutPart")
    public String submitForm(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart part, BindingResult theBindingResult, Model theModel) {
        theModel.addAttribute("outsourcedpart", part);

        // Logging values for debugging
        System.out.println("Min: " + part.getMin());
        System.out.println("Max: " + part.getMax());
        System.out.println("Inventory: " + part.getInv());

        // Check for validation errors in form fields
        if (theBindingResult.hasErrors()) {
            return "OutsourcedPartForm";  // Return form with error messages
        }

        try {
            // Ensure the inventory is valid (between min and max)
            int inv = part.getInv();
            if (inv < part.getMin() || inv > part.getMax()) {
                throw new IllegalArgumentException("Inventory must be between minimum and maximum values.");
            }
            // Save the part after successful validation
            OutsourcedPartService repo = context.getBean(OutsourcedPartServiceImpl.class);
            repo.save(part);

        } catch (IllegalArgumentException e) {
            // Handle exception by sending error message back to the form
            theModel.addAttribute("errorMessage", e.getMessage());
            return "OutsourcedPartForm";  // Return to form to show error
        }

        // Redirect to confirmation page or return to main screen
        return "confirmationPage";
    }
}









