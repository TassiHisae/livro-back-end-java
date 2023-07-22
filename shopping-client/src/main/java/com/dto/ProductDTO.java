package com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductDTO {

    @NotBlank
    private String productIdentifier;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Float price;

    private CategoryDTO category;

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public ProductDTO setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public ProductDTO setPrice(Float price) {
        this.price = price;
        return this;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public ProductDTO setCategory(CategoryDTO category) {
        this.category = category;
        return this;
    }
}
