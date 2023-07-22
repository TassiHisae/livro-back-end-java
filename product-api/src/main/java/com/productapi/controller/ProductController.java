package com.productapi.controller;

import com.productapi.repository.ProductRepository;
import com.productapi.dto.ProductDTO;
import com.exception.ProductNotFoundException;
import com.productapi.model.Product;
import com.productapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public List<ProductDTO> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/product/category/{categoryId}")
    public List<ProductDTO> getProductByCategory(@PathVariable("categoryId") Long categoryId) {
        return productService.getProductByCategoryId(categoryId);
    }

    @GetMapping("/product/{productIdentifier}")
    public ProductDTO findById(@PathVariable("productIdentifier") String productIdentifier) {
        ProductDTO product = productService.findByProductIdentifier(productIdentifier);

        if (Objects.nonNull(product)) {
            return product;
        }

        throw new ProductNotFoundException();
    }

    @PostMapping("/product")
    public ProductDTO newProduct(@Valid @RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @DeleteMapping("/product/{id}")
    public ProductDTO delete(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            productService.delete(id);
        }
        throw new ProductNotFoundException();
    }
}
