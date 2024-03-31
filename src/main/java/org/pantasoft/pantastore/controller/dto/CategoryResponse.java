package org.pantasoft.pantastore.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CategoryResponse{
    @NotNull(message = "idProduct cannot be null")
    UUID id;
    @NotNull(message = "idCategory cannot be null")
    String name;
    @NotNull(message = "productName cannot be null")
    String description;
}
