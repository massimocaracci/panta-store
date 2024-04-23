package org.pantasoft.pantastore.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.pantasoft.pantastore.controller.dto.ProductRequest;
import org.pantasoft.pantastore.controller.dto.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.pantasoft.pantastore.service.ProductService;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.accepted;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity <List<ProductResponse>> findProducts() {
        return ResponseEntity.ok()
                .contentType(APPLICATION_JSON)
                .body(productService.findProducts());
    }

    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductRequest request) {
        try {
            productService.addProduct(request);
            return accepted().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/{productId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProduct(@PathVariable("productId") UUID productId) {
        var productResponse = productService.getProduct(productId);
        log.info("Product found {}", productResponse);
        return ResponseEntity.ok(productResponse);
    }
}
