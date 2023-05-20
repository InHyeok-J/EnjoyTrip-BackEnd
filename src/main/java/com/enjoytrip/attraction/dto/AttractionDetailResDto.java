package com.enjoytrip.attraction.dto;

import com.enjoytrip.attraction.entity.AttractionReview;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDetailResDto {
    private Integer id;
    private Integer category;
    @NotBlank
    private String attractionName;
    private String address;
    private String attractionImageUrl;
    private String description;
}
