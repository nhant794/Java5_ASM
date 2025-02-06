package org.example.java5_asm.service;

import org.example.java5_asm.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    List<User> getAll();
    Optional<User> getById(Long id);
    User save(User user);
    void delete(Long id);
}
