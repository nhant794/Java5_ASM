package org.example.java5_asm.controller.auth;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Login {

    @GetMapping("/auth/login")
    public String login() {
        return "/auth/login";
    }

}
