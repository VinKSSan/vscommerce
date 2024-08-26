package com.vinsaned.vscommerce.repositories;

import com.vinsaned.vscommerce.entities.Order;
import com.vinsaned.vscommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
