package org.pantasoft.pantastore.mapper;

import org.pantasoft.pantastore.controller.dto.ProductRequest;
import org.pantasoft.pantastore.controller.dto.ProductResponse;
import org.pantasoft.pantastore.model.ProductEntity;
import org.pantasoft.pantastore.model.CategoryEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductEntity mapToEntity(ProductRequest dto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(dto.getCategoryId());

        return ProductEntity.builder()
                .productId(dto.getProductId())
                .category(categoryEntity)
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public static ProductResponse mapToDto(ProductEntity entity) {
        return ProductResponse.builder()
                .productId(entity.getProductId())
                .categoryId(entity.getCategory().getCategoryId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    public static List<ProductResponse> mapToDtos(List<ProductEntity> products) {
        return products.stream()
                .map(ProductMapper::mapToDto)
                .collect(Collectors.toList());
    }
}