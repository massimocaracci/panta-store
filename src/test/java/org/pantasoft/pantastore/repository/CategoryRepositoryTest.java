package org.pantasoft.pantastore.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pantasoft.pantastore.model.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void whenSave_thenFindsByName() {
        CategoryEntity category = new CategoryEntity();
        category.setCategoryId(UUID.randomUUID());
        category.setName("Test Category");
        category.setDescription("Test Description");

        entityManager.persistAndFlush(category);

        CategoryEntity foundCategory = categoryRepository.findByName(category.getName());

        assertThat(foundCategory).isNotNull();
        assertThat(foundCategory.getName()).isEqualTo(category.getName());
    }

    @Test
    void whenFindById_thenReturnCategory() {
        CategoryEntity category = new CategoryEntity();
        category.setCategoryId(UUID.randomUUID());
        category.setName("Test Category");
        category.setDescription("Test Description");

        entityManager.persistAndFlush(category);

        CategoryEntity foundCategory = categoryRepository.findById(category.getCategoryId()).orElse(null);

        assertThat(foundCategory).isNotNull();
        assertThat(foundCategory.getCategoryId()).isEqualTo(category.getCategoryId());
    }

    @Test
    void whenFindAll_thenReturnAllCategories() {
        CategoryEntity category1 = new CategoryEntity();
        category1.setCategoryId(UUID.randomUUID());
        category1.setName("Test Category 1");
        category1.setDescription("Test Description 1");

        CategoryEntity category2 = new CategoryEntity();
        category2.setCategoryId(UUID.randomUUID());
        category2.setName("Test Category 2");
        category2.setDescription("Test Description 2");

        entityManager.persistAndFlush(category1);
        entityManager.persistAndFlush(category2);

        List<CategoryEntity> allCategories = categoryRepository.findAll();

        assertThat(allCategories).hasSize(2).extracting(CategoryEntity::getName).containsOnly(category1.getName(), category2.getName());
    }

    @Test
    void whenDelete_thenRemovingShouldBeSuccessful() {
        CategoryEntity category = new CategoryEntity();
        category.setCategoryId(UUID.randomUUID());
        category.setName("Test Category");
        category.setDescription("Test Description");

        entityManager.persistAndFlush(category);
        categoryRepository.deleteById(category.getCategoryId());

        CategoryEntity foundCategory = categoryRepository.findById(category.getCategoryId()).orElse(null);

        assertThat(foundCategory).isNull();
    }
}