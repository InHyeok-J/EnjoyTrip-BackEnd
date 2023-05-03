package com.enjoytrip.attraction.service;

import com.enjoytrip.attraction.dto.AttractionReviewCreateDto;
import com.enjoytrip.attraction.entity.AttractionReview;

import java.util.List;

public interface AttractionReviewService {
    List<AttractionReview> getReviewsByAttractionId(String attractionId);
    List<AttractionReview> getReviewsByUserId(String userId);
    AttractionReview getOneReview(String reviewId);

    int writeReview(AttractionReviewCreateDto review);

    int deleteReview(String id);
}
