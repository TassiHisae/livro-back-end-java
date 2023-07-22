package com.productapi.repository;

import com.productapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByCategoryId(Long id);

    public Product findByProductIdentifier(String productIdentifier);
}
