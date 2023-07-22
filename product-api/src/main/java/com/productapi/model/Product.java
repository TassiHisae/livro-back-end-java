package com.productapi.model;

import com.productapi.dto.ProductDTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productIdentifier;
    private String name;
    private String description;
    private Float price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public static Product convert(ProductDTO productDTO){
        Product product = new Product()
                .setName(productDTO.getName())
                .setPrice(productDTO.getPrice())
                .setDescription(productDTO.getDescription())
                .setProductIdentifier(productDTO.getProductIdentifier());

        if(Objects.nonNull(productDTO.getCategory())){
            product.setCategory(Category.convert(productDTO.getCategory()));
        }

        return product;
    }

    public long getId() {
        return id;
    }

    public Product setId(long id) {
        this.id = id;
        return this;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public Product setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public Product setPrice(Float price) {
        this.price = price;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Product setCategory(Category category) {
        this.category = category;
        return this;
    }
}
