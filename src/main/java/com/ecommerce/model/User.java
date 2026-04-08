package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Primary key for the user.
    private Long id;

    @Column(nullable=false)
    // Display name shown in profiles and orders.
    private String name;

    @Column(unique=true,nullable=false)
    // Unique email used for login.
    private String email;

    @Column(nullable=false)
    @JsonIgnore
    // Hashed password stored securely and never serialized.
    private String password;

    @Column(nullable=false)
    // Role used for authorization (e.g., USER, ADMIN).
    private String role;

}
