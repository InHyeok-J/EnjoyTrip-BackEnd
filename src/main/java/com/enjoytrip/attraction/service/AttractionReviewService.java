package com.enjoytrip.attraction.service;

import com.enjoytrip.attraction.entity.AttractionReview;

import java.util.List;

public interface AttractionReviewService {
    List<AttractionReview> getReviewsByAttractionId();
    List<AttractionReview> getReviewsByUserId();
    int writeReview();
    int deleteReview();
}
