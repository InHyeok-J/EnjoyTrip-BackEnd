package com.enjoytrip.attraction.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AttractionSearchOptionsDto {
    private Integer sidoCode;
    private Integer gugunCode;
    private Integer category;
    private String title;
    private int limit = 10;
    private int offset;
}
