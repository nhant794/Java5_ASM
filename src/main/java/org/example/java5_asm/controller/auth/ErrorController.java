package org.example.java5_asm.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/show403")
    public String show403Page() {
        return "show403"; // Trả về file show403.html hoặc show403.jsp
    }
}
