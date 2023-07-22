package com.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ItemDTO {

    @NotBlank
    private String productIdentifier;

    @NotNull
    private Float price;

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public ItemDTO setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public ItemDTO setPrice(Float price) {
        this.price = price;
        return this;
    }
}
