package com.vinsaned.vscommerce.repositories;

import com.vinsaned.vscommerce.entities.Order;
import com.vinsaned.vscommerce.entities.OrderItem;
import com.vinsaned.vscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
