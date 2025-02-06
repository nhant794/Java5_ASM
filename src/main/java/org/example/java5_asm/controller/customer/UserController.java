package org.example.java5_asm.controller.customer;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {

    @GetMapping("/profile-user")
    public String userProfile(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "auth/profile";
    }
}
