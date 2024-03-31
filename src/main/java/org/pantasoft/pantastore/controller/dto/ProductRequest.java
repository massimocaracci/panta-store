package org.pantasoft.pantastore.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class ProductRequest {
    @NotNull(message = "productId cannot be null")
    UUID productId;
    @NotNull(message = "categoryId cannot be null")
    UUID categoryId;
    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "name cannot be empty")
    String name;
    @NotNull(message = "description cannot be null")
    String description;
    @NotNull(message = "price cannot be null")
    BigDecimal price;
}
