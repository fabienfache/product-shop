package com.atlen.productshop.model;

import com.fasterxml.jackson.annotation.JsonProperty;;
import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
/**
 * Product
 */
@Validated
@Data
public class Product   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("price")
  private Double price = null;

  @JsonProperty("quantity")
  private Integer quantity = null;

  @JsonProperty("inventoryStatus")
  private InventoryStatusEnum inventoryStatus = null;

  @JsonProperty("category")
  private String category = null;

  @JsonProperty("image")
  private String image = null;

  @JsonProperty("rating")
  private Double rating = null;

}
