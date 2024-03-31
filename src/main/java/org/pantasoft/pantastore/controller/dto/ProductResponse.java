package org.pantasoft.pantastore.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
public class ProductResponse {
    UUID productId;
    UUID categoryId;
    String name;
    String description;
    BigDecimal price;
}
