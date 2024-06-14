package com.vinsaned.vscommerce.repositories;

import com.vinsaned.vscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
