package com.enjoytrip.attraction.service;

import com.enjoytrip.attraction.dto.AttractionReviewCreateDto;
import com.enjoytrip.attraction.dto.AttractionReviewMypageDto;
import com.enjoytrip.attraction.dto.AttractionReviewResDto;
import com.enjoytrip.attraction.dto.AttractionReviewScoreDto;
import com.enjoytrip.attraction.entity.AttractionReview;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface AttractionReviewService {
    List<AttractionReviewResDto> getReviewsByAttractionId(String attractionId);
    List<AttractionReview> getReviewsByUserId(String userId);
    AttractionReview getOneReview(String reviewId);
    AttractionReviewScoreDto getEvaluation(String attractionId);

    Long writeReview(AttractionReviewCreateDto reviewCreateDto, MultipartFile file, Long userId);

    int deleteReview(String id);
    List<AttractionReviewMypageDto> getReviewsByUserIdForMyPage(Long userId);
}
