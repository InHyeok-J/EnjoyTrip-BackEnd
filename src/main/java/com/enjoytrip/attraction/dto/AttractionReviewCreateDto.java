package com.enjoytrip.attraction.dto;

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
    @NotBlank
    private Long userId;
    @NotBlank
    private Long attractionId;
}
