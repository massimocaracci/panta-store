package org.pantasoft.pantastore.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pantasoft.pantastore.model.ProductEntity;
import org.pantasoft.pantastore.model.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void whenPersist_thenProductShouldBeFound() {
        // given
        CategoryEntity category = new CategoryEntity();
        category.setCategoryId(UUID.randomUUID());
        category.setName("Test Category");
        categoryRepository.save(category);

        ProductEntity product = new ProductEntity();
        product.setProductId(UUID.randomUUID());
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setCategory(category);
        productRepository.save(product);

        // when
        ProductEntity found = productRepository.findById(product.getProductId()).orElse(null);

        // then
        assertNotNull(found);
        assertEquals(product.getName(), found.getName());
    }
}