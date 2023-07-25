package com.shoppingapi.client;

import com.dto.ProductDTO;
import com.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ProductService {

    @Value("${PRODUCT_API_URL:http:localhost:8082/product/}")
    private String productApiUrl;

    public ProductDTO getProductByIdentifier(String productIdentifier){
        try {
            WebClient webClient = WebClient.builder()
                    .baseUrl(productApiUrl)
                    .build();

            return webClient.get()
                    .uri(productIdentifier)
                    .retrieve()
                    .bodyToMono(ProductDTO.class).block();

        }catch (HttpClientErrorException.NotFound ex){
            throw new ProductNotFoundException();
        }
    }
}
