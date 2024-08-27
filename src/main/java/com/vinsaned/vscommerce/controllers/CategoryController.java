package com.vinsaned.vscommerce.controllers;

import com.vinsaned.vscommerce.dto.CategoryDTO;
import com.vinsaned.vscommerce.dto.ProductDTO;
import com.vinsaned.vscommerce.dto.ProductMinDTO;
import com.vinsaned.vscommerce.services.CategoryService;
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
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>>  findAll() {
        List<CategoryDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }
}
