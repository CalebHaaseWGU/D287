package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.service.InhousePartService;
import com.example.demo.service.InhousePartServiceImpl;
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
 *
 *
 *
 *
 */
@Controller
public class AddInhousePartController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddInPart")
    public String showFormAddInhousePart(Model theModel) {
        InhousePart inhousepart = new InhousePart();
        theModel.addAttribute("inhousepart", inhousepart);
        return "InhousePartForm";
    }

    @PostMapping("/showFormAddInPart")
    public String submitForm(@Valid @ModelAttribute("inhousepart") InhousePart part, BindingResult theBindingResult, Model theModel) {
        theModel.addAttribute("inhousepart", part);

        // Check for validation errors in form fields
        if (theBindingResult.hasErrors()) {
            return "InhousePartForm";
        }

        // Retrieve the temporary inventory value from the form
        try {
            int tempInv = part.getTempInv(); // Assuming 'tempInv' field is added in the InhousePart class for the form
            if (tempInv < part.getMin() || tempInv > part.getMax()) {
                throw new IllegalArgumentException("Inventory must be between minimum and maximum values.");
            }
            part.setInv(tempInv); // Set the actual inventory field after validation
        } catch (IllegalArgumentException e) {
            // Catch the exception and add an error message to the model
            theModel.addAttribute("errorMessage", e.getMessage());
            return "InhousePartForm"; // Return to the form to display the error
        }

        // Get the InhousePartService bean from the context
        InhousePartService repo = context.getBean(InhousePartServiceImpl.class);
        try {
            // Check if the part already exists and set its products
            InhousePart ip = repo.findById((int) part.getId());
            if (ip != null) {
                part.setProducts(ip.getProducts());
            }

            // Save the part, and this is where an IllegalArgumentException might be thrown
            repo.save(part);
        } catch (IllegalArgumentException e) {
            // Catch the exception and add an error message to the model
            theModel.addAttribute("errorMessage", e.getMessage());
            return "InhousePartForm"; // Return to the form to display the error
        }

        return "confirmationaddpart";
    }
}


