package org.example.java5_asm.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Register {

    @GetMapping("/auth/register")
    public String register() {
        return "auth/register";
    }
}
