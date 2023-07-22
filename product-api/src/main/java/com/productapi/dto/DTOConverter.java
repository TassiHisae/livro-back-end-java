package com.productapi.dto;

import com.productapi.model.Category;
import com.productapi.model.Product;

import java.util.Objects;

public class DTOConverter {

    public static ProductDTO convert(Product product){
        ProductDTO productDTO = new ProductDTO()
                .setName(product.getName())
                .setPrice(product.getPrice())
                .setProductIdentifier(product.getProductIdentifier())
                .setDescription(product.getDescription());

        if (Objects.nonNull(product.getCategory())){
            productDTO.setCategory(DTOConverter.convert(product.getCategory()));
        }

        return productDTO;
    }

    public static CategoryDTO convert(Category category){
        return new CategoryDTO()
                .setId(category.getId())
                .setName(category.getName());
    }
}
