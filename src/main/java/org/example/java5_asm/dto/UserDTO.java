package org.example.java5_asm.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String fullname;
    private String phone;
    private String email;
    private List<String> roles;
}
