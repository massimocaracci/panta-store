package org.pantasoft.pantastore.mapper;

import org.pantasoft.pantastore.controller.dto.CategoryRequest;
import org.pantasoft.pantastore.controller.dto.CategoryResponse;
import org.pantasoft.pantastore.model.CategoryEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryEntity mapToEntity(CategoryRequest dto) {
        return CategoryEntity.builder()
                .categoryId(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public static List<CategoryEntity> mapToEntities(List<CategoryRequest> dtos) {
        return dtos.stream()
                .map(CategoryMapper::mapToEntity)
                .collect(Collectors.toList());
    }

    public static List<CategoryResponse> mapToDtos(List<CategoryEntity> categories) {
        return categories.stream()
                .map(CategoryMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public static CategoryResponse mapToDto(CategoryEntity categoryEntity) {
        return CategoryResponse.builder()
                .id(categoryEntity.getCategoryId())
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .build();
    }
}
