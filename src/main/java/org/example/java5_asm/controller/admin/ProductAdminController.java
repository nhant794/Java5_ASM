package org.example.java5_asm.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/product")
public class ProductAdminController {

    @GetMapping
    public String product() {
        return "admin/product";
    }
}
