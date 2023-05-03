package com.enjoytrip.attraction.dto;

import com.enjoytrip.attraction.entity.AttractionCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AttractionSearchOptions {
    private int sidoCode;
    private int gugunCode;
    private int category;
    private String title;
}
