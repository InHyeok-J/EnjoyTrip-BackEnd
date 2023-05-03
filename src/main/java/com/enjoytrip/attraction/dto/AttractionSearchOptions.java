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

    private Integer sidoCode;
    private Integer gugunCode;
    private Integer category;
    private String title;
    private int limit = 10;
    private int offset;
}
