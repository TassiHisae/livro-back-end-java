package com.productapi.service;

import com.productapi.dto.ProductDTO;
import com.productapi.repository.ProductRepository;
import com.productapi.dto.DTOConverter;
import com.exception.CategoryNotFoundException;
import com.productapi.model.Product;
import com.productapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAll(){
        return productRepository.findAll()
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(Long id){
        return productRepository.findByCategoryId(id)
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier){
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if (Objects.nonNull(product)){
            return DTOConverter.convert(product);
        }
        return null;
    }

    public ProductDTO save(ProductDTO productDTO){
        Boolean existsCategory = categoryRepository.existsById(productDTO.getCategory().getId());

        if(!existsCategory){
            throw new CategoryNotFoundException();
        }

        Product product = productRepository.save(Product.convert(productDTO));
        return DTOConverter.convert(product);
    }

    public void delete(Long productId){
        Optional<Product> product = productRepository.findById(productId);

        if(product.isPresent()){
            productRepository.delete(product.get());
        }
    }
}
