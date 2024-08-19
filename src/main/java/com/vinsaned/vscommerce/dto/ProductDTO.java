package com.vinsaned.vscommerce.dto;

import com.vinsaned.vscommerce.entities.Product;
import javax.validation.constraints.*;

public class ProductDTO {
    private Long id;
    @Size(min = 3, max = 80, message = "Nome precisa ter mais de 3 e menos de 80 caracteres!")
    @NotBlank(message = "nome não pode estar vazio")
    private String name;
    @Size(min = 10, message = "descrição precisa ter mais de 10 caracteres!")
    @NotBlank(message = "campo requerido!")
    private String description;
    @Positive(message = "preço deve ser positivo!")
    private Double price;
    private String imgUrl;

    public ProductDTO(){}

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }
    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
