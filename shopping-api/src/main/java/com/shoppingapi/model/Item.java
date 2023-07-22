package com.shoppingapi.model;

import com.shoppingapi.dto.ItemDTO;

import javax.persistence.Embeddable;

@Embeddable
public class Item {

    private String productIdentifier;
    private Float price;

    public static Item convert(ItemDTO itemDTO) {
        return new Item()
                .setProductIdentifier(itemDTO.getProductIdentifier())
                .setPrice(itemDTO.getPrice());
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public Item setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public Item setPrice(Float price) {
        this.price = price;
        return this;
    }
}
