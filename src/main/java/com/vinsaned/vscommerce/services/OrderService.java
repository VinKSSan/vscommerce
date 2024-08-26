package com.vinsaned.vscommerce.services;

import com.vinsaned.vscommerce.dto.OrderDTO;
import com.vinsaned.vscommerce.dto.ProductDTO;
import com.vinsaned.vscommerce.entities.Order;
import com.vinsaned.vscommerce.entities.Product;
import com.vinsaned.vscommerce.repositories.OrderRepository;
import com.vinsaned.vscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findByID(Long id){
        Optional<Order> result = repository.findById(id);
        Order order = result.orElseThrow(
                ()->new ResourceNotFoundException("Recurso não encontrado")
        );
        OrderDTO dto = new OrderDTO(order);
        return  dto;
    }
}
