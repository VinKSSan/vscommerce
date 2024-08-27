package com.vinsaned.vscommerce.services;

import com.vinsaned.vscommerce.dto.OrderDTO;
import com.vinsaned.vscommerce.dto.OrderItemDTO;
import com.vinsaned.vscommerce.dto.ProductDTO;
import com.vinsaned.vscommerce.entities.Order;
import com.vinsaned.vscommerce.entities.OrderItem;
import com.vinsaned.vscommerce.entities.OrderStatus;
import com.vinsaned.vscommerce.entities.Product;
import com.vinsaned.vscommerce.repositories.OrderItemRepository;
import com.vinsaned.vscommerce.repositories.OrderRepository;
import com.vinsaned.vscommerce.repositories.ProductRepository;
import com.vinsaned.vscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private OrderItemRepository oIRepo;
    @Autowired
    private ProductRepository pRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findByID(Long id){
        Optional<Order> result = repository.findById(id);
        Order order = result.orElseThrow(
                ()->new ResourceNotFoundException("Recurso não encontrado")
        );
        OrderDTO dto = new OrderDTO(order);
        authService.validateSelfOrAdmin(order.getClient().getId());
        return  dto;
    }
    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(userService.authenticated());
        for (OrderItemDTO itemDTO : dto.getItems()){
            Product product = pRepo.getReferenceById(itemDTO.getProductId());
            OrderItem item = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }
        repository.save(order);
        oIRepo.saveAll(order.getItems());
        return new OrderDTO(order);
    }
}
