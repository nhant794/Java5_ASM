package org.example.java5_asm.repository;


import org.example.java5_asm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String Email);
}
