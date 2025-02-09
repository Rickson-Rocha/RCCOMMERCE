package com.rsolution.rcommerce.domain.product.dto;

import com.rsolution.rcommerce.domain.product.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public class ProductDto {
    private Long id;
    @NotBlank(message = "The name field is required")
    @Size(min = 3,max = 80,message = "The name filed must be between 3 and 80 characters")
    private String name;
    @Size(min = 10,message = "The description field must have at least 10 characters")
    private String description;
    @Positive(message = "The price field must be a positive number")
    private Double price;
    private String imageUrl;

    public ProductDto() {}

    public ProductDto(Long id, String name, String description, String imageUrl, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public ProductDto(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        imageUrl = entity.getImageUrl();
        price = entity.getPrice();
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

    public String getImageUrl() {
        return imageUrl;
    }
}
