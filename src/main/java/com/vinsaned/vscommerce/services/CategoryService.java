package com.vinsaned.vscommerce.services;

import com.vinsaned.vscommerce.dto.CategoryDTO;
import com.vinsaned.vscommerce.dto.ProductDTO;
import com.vinsaned.vscommerce.dto.ProductMinDTO;
import com.vinsaned.vscommerce.entities.Category;
import com.vinsaned.vscommerce.entities.Product;
import com.vinsaned.vscommerce.repositories.CategoryRepository;
import com.vinsaned.vscommerce.repositories.ProductRepository;
import com.vinsaned.vscommerce.services.exceptions.DataBaseException;
import com.vinsaned.vscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> result = repository.findAll();
        return  result.stream().map(CategoryDTO::new).toList();
    }
}
