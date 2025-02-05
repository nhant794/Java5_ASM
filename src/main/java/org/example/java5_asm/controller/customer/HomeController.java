package org.example.java5_asm.controller.customer;

import org.example.java5_asm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {

        return "customer/home";
    }
}
