package com.atlen.productshop.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoryEnum {

    Electronics("Electronics"),

    Accessories("Accessories"),

    Clothing("Clothing"),

    Fitness("Fitness");

    private String value;

    CategoryEnum(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static CategoryEnum fromValue(String text) {
        for (CategoryEnum b : CategoryEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

}
