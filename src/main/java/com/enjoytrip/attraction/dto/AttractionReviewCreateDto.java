package com.enjoytrip.attraction.dto;

import com.enjoytrip.attraction.entity.AttractionReview;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AttractionReviewCreateDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private Integer score;
    private Long userId;
    @NotBlank
    private Long attractionId;
    public AttractionReview toEntity(Long userId) {
        return AttractionReview.builder()
                .title(title)
                .content(content)
                .score(score)
                .userId(userId)
                .attractionId(attractionId)
                .build();
    }

}
