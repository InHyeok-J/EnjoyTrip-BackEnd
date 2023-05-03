package com.enjoytrip.attraction.controller;

import com.enjoytrip.attraction.dto.AttractionReviewCreateDto;
import com.enjoytrip.attraction.dto.AttractionSearchOptionsDto;
import com.enjoytrip.attraction.entity.Attraction;
import com.enjoytrip.attraction.entity.AttractionReview;
import com.enjoytrip.attraction.service.AttractionReviewService;
import com.enjoytrip.attraction.service.AttractionService;
import com.enjoytrip.global.response.JsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/attractions")
@RestController
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService service;
    private final AttractionReviewService reviewService;

    @GetMapping("")
    public ResponseEntity<?> search(AttractionSearchOptionsDto options) {
        List<Attraction> list = service.search(options);
        return JsonResponse.okWithData(HttpStatus.OK, "attraction 검색 성공!", list);
    }

    @GetMapping("/{attractionId}/reviews")
    public ResponseEntity<?> getReviewsByAttractionId(@PathVariable String attractionId) {
        System.out.println(attractionId);
        List<AttractionReview> list = reviewService.getReviewsByAttractionId(attractionId);
        return JsonResponse.okWithData(HttpStatus.OK, "관광지 아이디로 리뷰 검색 성공", list);
    }

    @PostMapping("/{attractionId}/reviews")
    public ResponseEntity<?> postReviews(@RequestBody AttractionReviewCreateDto review) {
        int res = reviewService.writeReview(review);
        if (res == 1) {
            return JsonResponse.okWithData(HttpStatus.OK, "관광지 리뷰 작성 성공", res);
        }
        return JsonResponse.fail("fail", 400);
    }

    @GetMapping("/{attractionId}/reviews/{reviewId}")
    public ResponseEntity<?> getOneReview(@PathVariable String attractionId, @PathVariable String reviewId) {
        AttractionReview review = reviewService.getOneReview(reviewId);
        return JsonResponse.okWithData(HttpStatus.OK, "관광지 리뷰 아이디로 조회 성공", review);
    }
}
