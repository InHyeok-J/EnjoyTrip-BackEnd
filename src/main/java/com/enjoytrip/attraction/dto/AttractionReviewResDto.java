package com.enjoytrip.attraction.dto;

import com.enjoytrip.attraction.entity.AttractionReview;
import com.enjoytrip.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttractionReviewResDto {
    private Long id;
    private String userId;
    private String nickname;
    private String userProfileImg;
    private AttractionReview review;
}
