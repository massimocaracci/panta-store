package org.pantasoft.pantastore.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.pantasoft.pantastore.controller.dto.CategoryRequest;
import org.pantasoft.pantastore.controller.dto.CategoryResponse;
import org.pantasoft.pantastore.mapper.CategoryMapper;
import org.pantasoft.pantastore.model.CategoryEntity;
import org.pantasoft.pantastore.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.pantasoft.pantastore.mapper.CategoryMapper.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findCategories() {
        return ResponseEntity.ok()
                .contentType(APPLICATION_JSON)
                .body(CategoryMapper.mapToDtos(categoryService.findCategories()));
    }

    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {

        return ResponseEntity.ok()
                .contentType(APPLICATION_JSON)
                .body(mapToDto(categoryService.createCategory(categoryRequest)));
    }

    @PostMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> editCategory(@PathVariable("id") String id, @Valid @RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok().contentType(APPLICATION_JSON)
                .body(categoryService.editCategory(UUID.fromString(id), categoryRequest));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
