package org.pantasoft.pantastore.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode
public class CategoryRequest {

    UUID id;
    @NotNull
    @Size(min = 1, max = 100)
    String name;
    String description;
}
