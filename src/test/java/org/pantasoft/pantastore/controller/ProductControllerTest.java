package org.pantasoft.pantastore.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pantasoft.pantastore.UnityTestUtils;
import org.pantasoft.pantastore.controller.dto.ProductRequest;
import org.pantasoft.pantastore.controller.dto.ProductResponse;
import org.pantasoft.pantastore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProductControllerTest extends UnityTestUtils {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductController productController;

    private ProductService productServiceMock;

    @BeforeEach
    void setUp() {

        productServiceMock = mock(ProductService.class);
        productController = new ProductController(productServiceMock);
        mockMvc = MockMvcBuilders
                .standaloneSetup(productController)
                .build();
    }

    @Test
    void findProducts_EmptyStore_ReturnsEmptyList() throws Exception {

        when(productServiceMock.findProducts()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", empty()));
    }

    @Test
    void findProducts_NotEmptyStore_ReturnsProductList() throws Exception {

        var productResponse = givenOneObjectOf(ProductResponse.class);
        when(productServiceMock.findProducts()).thenReturn(
                List.of(productResponse)
        );

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void addProduct_ValidProduct_ReturnsAccepted() throws Exception {

        var productRequest = givenOneObjectOf(ProductRequest.class);
        var productResponse = givenOneObjectOf(ProductResponse.class);

        when(productServiceMock.findProducts()).thenReturn(
                List.of(productResponse)
        );
        mockMvc.perform(post("/products")
                        .contentType(APPLICATION_JSON)
                        .content(asJsonString(productRequest )))
                .andExpect(status().isAccepted());
    }

    @Test
    void addProduct_InvalidIdProduct_ReturnsBadRequest() throws Exception {

        var productResponse = givenOneObjectOf(ProductResponse.class)
                .toBuilder().productId(null).build();

        mockMvc.perform(post("/products")
                        .contentType(APPLICATION_JSON)
                        .content(asJsonString(productResponse)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addProduct_InvalidNameProduct_ReturnsBadRequest() throws Exception {

        var productRequest = givenOneObjectOf(ProductRequest.class)
                .toBuilder().name("").build();

        mockMvc.perform(post("/products")
                        .contentType(APPLICATION_JSON)
                        .content(asJsonString(productRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getProduct_ValidIdProduct_ReturnsProduct() throws Exception {

        var productResponse = givenOneObjectOf(ProductResponse.class);
        when(productServiceMock.getProduct(productResponse.getProductId())).thenReturn(productResponse);

        mockMvc.perform(get("/products/" + productResponse.getProductId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").value(productResponse.getProductId().toString()))
                .andExpect(jsonPath("$.categoryId").value(productResponse.getCategoryId().toString()))
                .andExpect(jsonPath("$.name").value(productResponse.getName()))
                .andExpect(jsonPath("$.description").value(productResponse.getDescription()))
                .andExpect(jsonPath("$.price").value(productResponse.getPrice().toString()));
    }

}