package com.enjoytrip.attraction.entity;

import lombok.*;

@Getter
@NoArgsConstructor
@Setter
@Builder
@AllArgsConstructor
@ToString
public class Attraction {
    private Long id;
    private AttractionCategory category;
    private String attractionName;
    private String attractionImageUrl;
    private Double latitude;
    private Double longitude;
    private int sidoCode;
    private int gugunCode;
}