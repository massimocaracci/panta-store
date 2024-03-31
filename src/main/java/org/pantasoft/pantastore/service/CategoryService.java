package org.pantasoft.pantastore.service;

import lombok.extern.slf4j.Slf4j;
import org.pantasoft.pantastore.controller.dto.CategoryRequest;
import org.pantasoft.pantastore.controller.dto.CategoryResponse;
import org.pantasoft.pantastore.mapper.CategoryMapper;
import org.pantasoft.pantastore.model.CategoryEntity;
import org.pantasoft.pantastore.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.pantasoft.pantastore.mapper.CategoryMapper.*;

@Slf4j
@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(final CategoryRepository repository) {
        this.repository = repository;
    }


    public List<CategoryEntity> findCategories() {
        return repository.findAll();
    }

    public CategoryEntity createCategory(CategoryRequest categoryRequest) {
        return repository.save(mapToEntity(categoryRequest));
    }

   public CategoryResponse editCategory(UUID categoryId , CategoryRequest categoryRequest) {
    return repository.findById(categoryId)
            .map(categoryEntity -> {
                categoryEntity.setName(categoryRequest.getName());
                // update other fields as necessary
                return mapToDto(repository.save(categoryEntity));
            })
            .orElseThrow(() -> new RuntimeException("Category not found with id " + categoryId));
    }

    public void deleteCategory(UUID id) {
        repository.deleteById(id);
    }
}
