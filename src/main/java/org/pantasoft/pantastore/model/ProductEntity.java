package org.pantasoft.pantastore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    UUID productId;
    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    CategoryEntity category;
    String name;
    String description;
}
