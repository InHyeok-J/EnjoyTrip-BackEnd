package com.enjoytrip.attraction.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AttractionListResDto {
    private Long id;
    private int category;
    private String categoryMean;
    private String attractionName;
    private String address;
    private String attractionImageUrl;
    private int reviewsCount;
    private double score;
    private int sidoCode;
    private int gugunCode;
}
