package com.enjoytrip.attraction.dto;

import com.enjoytrip.attraction.entity.AttractionReview;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AttractionReviewCreateDto {
    private String title;
    private String content;
    private Integer score;
    private Long attractionId;

    public AttractionReviewCreateDto(String title, String content, Integer score,
        Long attractionId) {
        this.title = title;
        this.content = content;
        this.score = score;
        this.attractionId = attractionId;
    }

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
