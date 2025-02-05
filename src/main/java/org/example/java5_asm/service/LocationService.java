package org.example.java5_asm.service;


import org.example.java5_asm.model.District;
import org.example.java5_asm.model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LocationService {

    private static final String PROVINCE_API_URL = "https://provinces.open-api.vn/api/p/";
    private static final String DISTRICT_API_URL = "https://provinces.open-api.vn/api/p/{provinceId}?depth=2";

    @Autowired
    private RestTemplate restTemplate;

    // Lấy danh sách tỉnh thành
    public List<Province> getProvinces() {
        Province[] provinces = restTemplate.getForObject(PROVINCE_API_URL, Province[].class);
        return List.of(provinces);
    }

    // Lấy danh sách quận huyện theo tỉnh
    public List<District> getDistricts(int provinceId) {
        String url = DISTRICT_API_URL + provinceId + "?depth=2";
        Province province = restTemplate.getForObject(url, Province.class);

        if (province != null && province.getDistricts() != null) {
            return province.getDistricts();
        }
        return List.of();
    }
}
