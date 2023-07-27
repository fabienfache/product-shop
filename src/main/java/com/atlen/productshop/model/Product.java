package com.atlen.productshop.model;

import com.fasterxml.jackson.annotation.JsonProperty;;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

/**
 * Product
 */
@Validated
@Data
@Entity
@Table(name="PRODUCT")
@EqualsAndHashCode
public class Product   {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
          generator = "product_seq")
  @SequenceGenerator(name="product_seq",sequenceName="PRODUCT_SEQ", allocationSize=1)
  @JsonProperty("id")
  @Column(name = "id")
  private Long id = null;

  @JsonProperty("code")
  @Column(name = "code")
  private String code = null;

  @JsonProperty("name")
  @Column(name = "name")
  private String name = null;

  @JsonProperty("description")
  @Column(name = "description")
  private String description = null;

  @JsonProperty("price")
  @Column(name = "price")
  private Double price = null;

  @JsonProperty("quantity")
  @Column(name = "quantity")
  private Integer quantity = null;

  @JsonProperty("inventoryStatus")
  @Column(name = "INVENTORYSTATUS")
  @Enumerated(EnumType.STRING)
  private InventoryStatusEnum inventoryStatus = null;

  @JsonProperty("category")
  @Column(name = "category")
  private String category = null;

  @JsonProperty("image")
  @Column(name = "image")
  private String image = null;

  @JsonProperty("rating")
  @Column(name = "rating")
  private Double rating = null;

}
