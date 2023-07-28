package com.atlen.productshop.model;

import com.atlen.productshop.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

/**
 * Product
 */
@Validated
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {


  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("code")
  @Max(value = 30)
  private String code = null;

  @JsonProperty("name")
  @Max(value = 70)
  private String name = null;

  @JsonProperty("description")
  @Max(value = 255)
  private String description = null;

  @JsonProperty("price")
  @Max(value = 30)
  @DecimalMin("0.01")
  private Double price = null;

  @JsonProperty("quantity")
  private Integer quantity = null;

  @JsonProperty("inventoryStatus")
  @Enumerated(EnumType.STRING)
  private InventoryStatusEnum inventoryStatus = null;

  @JsonProperty("category")
  @Enumerated(EnumType.STRING)
  private CategoryEnum category = null;

  @JsonProperty("image")
  @Column(name = "image")
  private String image = null;

  @JsonProperty("rating")
  @DecimalMax("5.0")
  @DecimalMin("0.0")
  private Double rating = null;

  public static ProductDto of(Product product) {
    return (product != null) ? builder()
            .id(product.getId())
            .category(CategoryEnum.fromValue(product.getCategory()))
            .code(product.getCode())
            .description(product.getDescription())
            .image(product.getImage())
            .inventoryStatus(InventoryStatusEnum.fromValue(product.getInventoryStatus()))
            .name(product.getName())
            .price(product.getPrice())
            .quantity(product.getQuantity())
            .rating(product.getRating())
            .build()
            : null;
  }
}
