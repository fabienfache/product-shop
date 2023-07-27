package com.atlen.productshop.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;


@Validated
public enum InventoryStatusEnum {
    LOWSTOCK("LOWSTOCK"),

    INSTOCK("INSTOCK"),

    OUTOFSTOCK("OUTOFSTOCK");

    private String value;

    InventoryStatusEnum(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static InventoryStatusEnum fromValue(String text) {
        for (InventoryStatusEnum b : InventoryStatusEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}