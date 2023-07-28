package com.atlen.productshop.entity;

import com.atlen.productshop.model.CategoryEnum;
import com.atlen.productshop.model.InventoryStatusEnum;
import com.atlen.productshop.model.ProductDto;
import com.fasterxml.jackson.annotation.JsonProperty;;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@Data
@Entity
@Table(name="PRODUCT")
@EqualsAndHashCode
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Product   {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "product_seq")
    @SequenceGenerator(name="product_seq",sequenceName="PRODUCT_SEQ", allocationSize=1)
    @Column(name = "ID")
    private Long id = null;

    @Column(name = "CODE")
    private String code = null;

    @Column(name = "NAME")
    private String name = null;

    @Column(name = "DESCRIPTION")
    private String description = null;

    @Column(name = "PRICE")
    private Double price = null;

    @Column(name = "QUANTITY")
    private Integer quantity = null;

    @Column(name = "INVENTORYSTATUS")
    private String inventoryStatus = null;

    @Column(name = "CATEGORY")
    private String category = null;

    @Column(name = "IMAGE")
    private String image = null;

    @JsonProperty("RATING")
    private Double rating = null;

    public static Product of(ProductDto product) {
        return (product != null) ? builder()
                .id(product.getId())
                .category((product.getCategory()!=null)?product.getCategory().toString():null)
                .code(product.getCode())
                .description(product.getDescription())
                .image(product.getImage())
                .inventoryStatus((product.getCategory()!=null)?product.getInventoryStatus().toString():null)
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .rating(product.getRating())
                .build()
                : null;
    }

}
