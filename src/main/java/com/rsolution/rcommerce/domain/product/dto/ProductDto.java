package com.rsolution.rcommerce.domain.product.dto;

import com.rsolution.rcommerce.domain.product.Product;


public class ProductDto {
    private Long id;
    private String name;
    private String description;
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
