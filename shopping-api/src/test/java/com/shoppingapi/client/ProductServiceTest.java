package com.shoppingapi.client;

import com.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    public static MockWebServer mockBackEnd;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() throws IOException{
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();

        String baseURL = String.format("http://localhost:%s", mockBackEnd.getPort());
        ReflectionTestUtils.setField(productService, "productApiUrl", baseURL);
    }

    @AfterEach
    void tearDown() throws IOException{
        mockBackEnd.shutdown();
    }

    @Test
    void testGetProductByIdentifier() throws Exception{
        ProductDTO productDTO = new ProductDTO()
                .setProductIdentifier("prod-identifier")
                .setPrice(100F);

        ObjectMapper objectMapper = new ObjectMapper();

        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(productDTO))
                .addHeader("Content-Type", "application/json"));

        productDTO = productService.getProductByIdentifier("prod-identifier");

        Assertions.assertEquals(100F, productDTO.getPrice());
        Assertions.assertEquals("prod-identifier", productDTO.getProductIdentifier());
    }
}
