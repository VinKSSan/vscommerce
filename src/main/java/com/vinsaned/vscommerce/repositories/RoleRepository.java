package com.vinsaned.vscommerce.repositories;

import com.vinsaned.vscommerce.entities.Product;
import com.vinsaned.vscommerce.entities.Role;
import com.vinsaned.vscommerce.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByAuthority(String authority);
}
