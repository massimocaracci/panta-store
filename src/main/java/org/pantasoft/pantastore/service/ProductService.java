package org.pantasoft.pantastore.service;

import org.pantasoft.pantastore.controller.dto.ProductRequest;
import org.pantasoft.pantastore.controller.dto.ProductResponse;
import org.pantasoft.pantastore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.pantasoft.pantastore.mapper.ProductMapper.*;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    public List<ProductResponse> findProducts() {

        return mapToDtos(productRepository.findAll());
    }

    public void addProduct(ProductRequest request) {

        productRepository.save(mapToEntity(request));
    }

    public ProductResponse getProduct(UUID productId) {

        return mapToDto(productRepository
                        .findById(productId)
                        .orElseThrow());
    }
}
