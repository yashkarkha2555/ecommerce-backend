package com.ecommerce.repository;

import com.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Look up user by unique email address.
    Optional<User> findByEmail(String email);

    // Fast existence check to avoid full entity load.
    boolean existsByEmail(String email);
}
