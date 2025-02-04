package org.example.java5_asm.controller.customer;

import org.example.java5_asm.entity.District;
import org.example.java5_asm.entity.Province;
import org.example.java5_asm.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/account")
    public String getAccountPage(Model model) {
        // Lấy danh sách tỉnh thành
        List<Province> provinces = locationService.getProvinces();
        model.addAttribute("provinces", provinces);
        return "auth/account";  // Trả về trang account.html
    }

    @ResponseBody
    public List<District> getDistricts(@RequestParam int provinceId) {
        // Lấy danh sách quận huyện của tỉnh
        return locationService.getDistricts(provinceId);
    }
}
