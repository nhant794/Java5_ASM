package org.example.java5_asm.service;

import org.example.java5_asm.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();
    Optional<User> getById(Long id);
    User save(User user);
    void delete(Long id);
}
