package com.vinsaned.vscommerce.controllers;

import com.vinsaned.vscommerce.dto.ProductDTO;
import com.vinsaned.vscommerce.entities.Product;
import com.vinsaned.vscommerce.repositories.ProductRepository;
import com.vinsaned.vscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id){
        ProductDTO dto = service.findByID(id);
       return dto;
    }
}
