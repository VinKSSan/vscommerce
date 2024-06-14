package com.vinsaned.vscommerce.services;

import com.vinsaned.vscommerce.dto.ProductDTO;
import com.vinsaned.vscommerce.entities.Product;
import com.vinsaned.vscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findByID(Long id){
        Optional<Product> result = repository.findById(id);
        Product product = result.get();
        ProductDTO dto = new ProductDTO(product);
        return  dto;
    }
}
