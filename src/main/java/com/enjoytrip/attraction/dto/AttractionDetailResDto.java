package com.enjoytrip.attraction.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AttractionDetailResDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private Integer score;
    @NotNull
    private Long attractionId;
}
