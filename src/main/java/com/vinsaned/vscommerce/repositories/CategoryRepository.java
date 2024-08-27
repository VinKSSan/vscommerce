package com.vinsaned.vscommerce.repositories;

import com.vinsaned.vscommerce.entities.Category;
import com.vinsaned.vscommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
