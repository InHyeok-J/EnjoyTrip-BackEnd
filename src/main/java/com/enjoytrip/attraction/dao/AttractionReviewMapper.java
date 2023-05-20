package com.enjoytrip.attraction.dao;

import com.enjoytrip.attraction.dto.AttractionReviewCreateDto;
import com.enjoytrip.attraction.dto.AttractionReviewResDto;
import com.enjoytrip.attraction.dto.AttractionReviewScoreDto;
import com.enjoytrip.attraction.entity.AttractionReview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionReviewMapper {
    List<AttractionReviewResDto> getReviewsByAttractionId(String attractionId);
    List<AttractionReview> getReviewsByUserId(String userId);

    AttractionReview getOneReview(String reviewId);
    Long writeReview(AttractionReview review);
    int deleteReview(String reviewId);

    AttractionReviewScoreDto getEvaluation(String attractionId);
}
