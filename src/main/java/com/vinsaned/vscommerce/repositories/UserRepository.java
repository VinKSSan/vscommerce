package com.vinsaned.vscommerce.repositories;

import com.vinsaned.vscommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
