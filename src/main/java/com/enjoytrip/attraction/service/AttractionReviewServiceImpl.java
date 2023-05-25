package com.enjoytrip.attraction.service;

import com.enjoytrip.attraction.dao.AttractionReviewMapper;
import com.enjoytrip.attraction.dto.AttractionReviewCreateDto;
import com.enjoytrip.attraction.dto.AttractionReviewMypageDto;
import com.enjoytrip.attraction.dto.AttractionReviewResDto;
import com.enjoytrip.attraction.dto.AttractionReviewScoreDto;
import com.enjoytrip.attraction.entity.AttractionReview;
import com.enjoytrip.global.common.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AttractionReviewServiceImpl implements AttractionReviewService {

    private final AttractionReviewMapper dao;
    private final S3Uploader s3Uploader;

    @Override
    public List<AttractionReviewResDto> getReviewsByAttractionId(String attractionId) {
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
    public AttractionReviewScoreDto getEvaluation(String attractionId) {
        return dao.getEvaluation(attractionId);
    }

    @Override
    public int deleteReview(String id) {
        return dao.deleteReview(id);
    }

    @Override
    public List<AttractionReviewMypageDto> getReviewsByUserIdForMyPage(Long userId) {
        return dao.getReviewsByUserIdForMyPage(userId);
    }

    @Override
    public Long writeReview(AttractionReviewCreateDto reviewCreateDto, MultipartFile file, Long userId) {
        AttractionReview review = reviewCreateDto.toEntity(userId);
        if (file != null) {
            String url = s3Uploader.uploadFile(file);
            review.setImageUrl(url);
        }
        return dao.writeReview(review);
    }
}
