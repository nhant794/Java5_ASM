//package org.example.java5_asm.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Tắt CSRF (tùy vào dự án)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/admin/**").permitAll() // Cho phép truy cập tất cả API dưới /admin/*
//                        .anyRequest().authenticated() // Các API khác vẫn cần xác thực
//                )
//                .formLogin(login -> login.disable()) // Tắt login mặc định
//                .httpBasic(basic -> basic.disable()); // Tắt basic auth
//
//        return http.build();
//    }
//}
