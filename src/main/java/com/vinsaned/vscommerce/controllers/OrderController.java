package com.vinsaned.vscommerce.controllers;

import com.vinsaned.vscommerce.dto.OrderDTO;
import com.vinsaned.vscommerce.dto.ProductDTO;
import com.vinsaned.vscommerce.dto.ProductMinDTO;
import com.vinsaned.vscommerce.services.OrderService;
import com.vinsaned.vscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id){
        OrderDTO dto = service.findByID(id);
       return ResponseEntity.ok(dto);
    }

}
