package com.enjoytrip.attraction.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum AttractionCategory {
    ATTRACTION(12), STAY(32), EATERY(39), COURSE(25), ENTERTAIN(14);

    AttractionCategory(int CODE) {
        this.CODE = CODE;
    }

    public int getCODE() {
        return CODE;
    }

    private final int CODE;

    public static AttractionCategory valueOfCode(int code) {
        return Arrays.stream(AttractionCategory.values()).filter((category) -> category.getCODE() == code).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    @JsonCreator
    public static AttractionCategory getJson(String code) {
        for (AttractionCategory category : AttractionCategory.values()) {
            if (category.getCODE() == Integer.parseInt(code)) {
                return category;
            }
        }
        return null;
    }
}
