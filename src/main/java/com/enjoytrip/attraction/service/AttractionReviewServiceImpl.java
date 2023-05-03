package com.enjoytrip.attraction.service;

import com.enjoytrip.attraction.dao.AttractionReviewMapper;
import com.enjoytrip.attraction.entity.AttractionReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionReviewServiceImpl implements AttractionReviewService {
    private final AttractionReviewMapper dao;
    @Override
    public List<AttractionReview> getReviewsByAttractionId() {
        return dao.getReviewsByAttractionId();
    }

    @Override
    public List<AttractionReview> getReviewsByUserId() {
        return dao.getReviewsByUserId();
    }

    @Override
    public int writeReview() {
        return dao.writeReview();
    }

    @Override
    public int deleteReview() {
        return dao.deleteReview();
    }
}
