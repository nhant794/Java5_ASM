package org.example.java5_asm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(AuthService authService) {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder());
//        provider.setUserDetailsService(authService);
//        return provider;
//    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Tạm thời tắt CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/order/**").hasAnyRole("ADMIN", "CUSTOMER")
                        .requestMatchers("/reviews/add").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers("/api/notify/**").authenticated()
                        .requestMatchers(
                                "/login/**",
                                "/confirmation/**",
                                "/address/**",
                                "/css/**",
                                "/js/**",
                                "/login-style/**",
                                "/images/**",
                                "/uploads/img/Perfume/**",
                                "/uploads/img/Account/**",
                                "/uploads/img/Description/**",
                                "/videos/**",
                                "/",
                                "/account/sign-up/**",
                                "/account/forgot-password/**",
                                "/category",
                                "/product-detail/*",
                                "/brand/**",
                                "/cart/**",
                                "/cartItem/**",
                                "/category/**",
                                "/perfume/**",
                                "/search/**",
                                "/ws/**",
                                "/api/auth/status",
                                "/favicon.ico/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .successHandler(authenticationSuccessHandler()) // Custom success handler
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(cfg -> cfg.accessDeniedPage("/showPage403"))
                .requestCache(requestCache -> requestCache.requestCache(new HttpSessionRequestCache()));

        return http.build();
    }
}
