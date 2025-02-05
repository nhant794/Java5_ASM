package org.example.java5_asm.controller.admin;

import org.example.java5_asm.model.User;
import org.example.java5_asm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/user")
public class UserAdminController {

    private final UserService userService;

    @Autowired
    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    // Hiển thị danh sách user
    @GetMapping
    public String index(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.getAll());
        System.out.println("user: " + userService.getAll());
        return "admin/user";
    }

    // Xóa user
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/user";
    }

    // Sửa user
    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Optional<User> user = userService.getById(id);
        user.ifPresent(value -> model.addAttribute("user", value));
        model.addAttribute("users", userService.getAll());
        return "admin/user";
    }

    // Lưu user (thêm hoặc cập nhật)
    @PostMapping
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user); // Hibernate tự động cập nhật nếu ID tồn tại
        return "redirect:/admin/user";
    }


}
