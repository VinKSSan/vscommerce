package com.vinsaned.vscommerce.services;

import com.vinsaned.vscommerce.dto.CategoryDTO;
import com.vinsaned.vscommerce.dto.ProductDTO;
import com.vinsaned.vscommerce.dto.ProductMinDTO;
import com.vinsaned.vscommerce.entities.Category;
import com.vinsaned.vscommerce.entities.Product;
import com.vinsaned.vscommerce.repositories.ProductRepository;
import com.vinsaned.vscommerce.services.exceptions.DataBaseException;
import com.vinsaned.vscommerce.services.exceptions.ResourceNotFoundException;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findByID(Long id){
        Optional<Product> result = repository.findById(id);
        Product product = result.orElseThrow(
                ()->new ResourceNotFoundException("Recurso não encontrado")
        );
        ProductDTO dto = new ProductDTO(product);
        return  dto;
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(Pageable pageable){
        Page<Product> products = repository.findAll(pageable);
        return  products.map(p -> new ProductMinDTO(p));
    }
    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findByName(String name, Pageable pageable){
        Page<Product> products = repository.scByName(name, pageable);
        return  products.map(ProductMinDTO::new);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update( Long id, ProductDTO dto){
        try{
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        }
        catch (EntityNotFoundException e){
            throw  new ResourceNotFoundException("id inválido, recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("não foi possivel deletar, recurso não encontrado");
        }
        catch (DataIntegrityViolationException e){
            throw new DataBaseException("Falha de integridade");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        entity.getCategories().clear();
        for(CategoryDTO catdto: dto.getCategories()){
            Category cat = new Category();
            cat.setId(catdto.getId());
            entity.getCategories().add(cat);
        }
    }
}
