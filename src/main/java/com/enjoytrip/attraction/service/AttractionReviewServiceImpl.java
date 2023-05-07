package com.enjoytrip.attraction.service;

import com.enjoytrip.attraction.dao.AttractionReviewMapper;
import com.enjoytrip.attraction.dto.AttractionReviewCreateDto;
import com.enjoytrip.attraction.entity.AttractionReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionReviewServiceImpl implements AttractionReviewService {
    private final AttractionReviewMapper dao;

    @Override
    public List<AttractionReview> getReviewsByAttractionId(String attractionId) {
        return dao.getReviewsByAttractionId(attractionId);
    }

    @Override
    public List<AttractionReview> getReviewsByUserId(String userId) {
        return dao.getReviewsByUserId(userId);
    }

    @Override
    public AttractionReview getOneReview(String reviewId) {
        return dao.getOneReview(reviewId);
    }

    @Override
    public int writeReview(AttractionReview review) {
        return dao.writeReview(review);
    }

    @Override
    public int deleteReview(String id) {
        return dao.deleteReview(id);
    }
}
