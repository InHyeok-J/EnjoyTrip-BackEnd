package com.enjoytrip.attraction.dao;

import com.enjoytrip.attraction.dto.AttractionReviewCreateDto;
import com.enjoytrip.attraction.entity.AttractionReview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionReviewMapper {
    List<AttractionReview> getReviewsByAttractionId(String attractionId);
    List<AttractionReview> getReviewsByUserId(String userId);

    AttractionReview getOneReview(String reviewId);
    int writeReview(AttractionReviewCreateDto review);
    int deleteReview(String reviewId);
}
