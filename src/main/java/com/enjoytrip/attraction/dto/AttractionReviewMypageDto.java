package com.enjoytrip.attraction.dto;

import com.enjoytrip.attraction.entity.AttractionReview;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttractionReviewMypageDto {
    private Long attractionId;
    private String attractionName;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
