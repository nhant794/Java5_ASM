package org.example.java5_asm.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Evaluate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer rating;
    private String comment;

    // Getters and Setters
}
