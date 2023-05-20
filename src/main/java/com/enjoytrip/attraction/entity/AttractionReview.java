package com.enjoytrip.attraction.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@NoArgsConstructor
@Setter
@Builder
@AllArgsConstructor
@ToString
public class AttractionReview {
    @JsonProperty("reviewId")
    private Long id;
    private String title;
    private String content;
    private int score;
    private Long userId;
    private Long attractionId;
    private Date createdAt;

}
