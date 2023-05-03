package com.enjoytrip.attraction.dao;

import com.enjoytrip.attraction.entity.AttractionReview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionReviewMapper {
    List<AttractionReview> getReviewsByAttractionId();
    List<AttractionReview> getReviewsByUserId();
    int writeReview();
    int deleteReview();

}
