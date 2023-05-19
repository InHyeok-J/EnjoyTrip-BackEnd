package com.enjoytrip.attraction.controller;

import com.enjoytrip.attraction.dto.AttractionListResDto;
import com.enjoytrip.attraction.dto.AttractionReviewCreateDto;
import com.enjoytrip.attraction.dto.AttractionSearchOptionsDto;
import com.enjoytrip.attraction.entity.Attraction;
import com.enjoytrip.attraction.entity.AttractionReview;
import com.enjoytrip.attraction.service.AttractionReviewService;
import com.enjoytrip.attraction.service.AttractionService;
import com.enjoytrip.global.dto.SessionUser;
import com.enjoytrip.global.response.JsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("/attractions")
@RestController
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService service;
    private final AttractionReviewService reviewService;

    @GetMapping("")
    public ResponseEntity<?> search(AttractionSearchOptionsDto options) {
        List<AttractionListResDto> list = service.search(options);
        return JsonResponse.okWithData(HttpStatus.OK, "attraction 검색 성공!", list);
    }

    @GetMapping("/{attractionId}/reviews")
    public ResponseEntity<?> getReviewsByAttractionId(@NotBlank @PathVariable String attractionId) {
        List<AttractionReview> list = reviewService.getReviewsByAttractionId(attractionId);
        return JsonResponse.okWithData(HttpStatus.OK, "관광지 아이디로 리뷰 검색 성공", list);
    }

    @PostMapping("/{attractionId}/reviews")
    public ResponseEntity<?> postReviews(@Valid @RequestBody AttractionReviewCreateDto reviewCreateDto, @AuthenticationPrincipal SessionUser sessionUser) {
        AttractionReview review = reviewCreateDto.toEntity(sessionUser.getId());
        int res = reviewService.writeReview(review);
        if (res == 1) {
            return JsonResponse.ok(HttpStatus.OK, "관광지 리뷰 작성 성공");
        }
        return JsonResponse.fail("fail", HttpStatus.BAD_REQUEST.value());
    }

    @GetMapping("/{attractionId}/reviews/{reviewId}")

    public ResponseEntity<?> getOneReview(@NotBlank @PathVariable String reviewId) {
        AttractionReview review = reviewService.getOneReview(reviewId);
        if (review == null) {
            return JsonResponse.fail("리뷰가 없습니다", HttpStatus.NOT_FOUND.value());
        }
        return JsonResponse.okWithData(HttpStatus.OK, "관광지 리뷰 아이디로 조회 성공", review);
    }

    @DeleteMapping("/{attractionId}/reviews/{reviewId}")
    public ResponseEntity<?> deleteOneReview(@NotBlank @PathVariable String reviewId) {
        int res = reviewService.deleteReview(reviewId);
        if (res == 1) {
            return JsonResponse.okWithData(HttpStatus.OK, "관광지 리뷰 삭제 성공", res);
        }
        return JsonResponse.fail("fail", HttpStatus.NOT_FOUND.value());
    }
}
