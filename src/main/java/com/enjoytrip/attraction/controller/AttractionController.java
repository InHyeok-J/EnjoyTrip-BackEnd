package com.enjoytrip.attraction.controller;

import com.enjoytrip.attraction.dto.*;
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
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    @GetMapping("/hot")
    public ResponseEntity<?> getHotAttraction() {
        AttractionListResDto dto = service.getHotAttraction();
        return JsonResponse.okWithData(HttpStatus.OK, "attraction hot 조회 성공!", dto);
    }

    @GetMapping("/recommend")
    public ResponseEntity<?> getRecommendedAttraction(@AuthenticationPrincipal SessionUser sessionUser) {
        if (sessionUser == null)
            return JsonResponse.fail("로그인 안 되어 있음", HttpStatus.BAD_REQUEST.value());
        List<AttractionListResDto> attraction = service.getRecommend(sessionUser.getId());
        return JsonResponse.okWithData(HttpStatus.OK, "attraction recommend 조회 성공!", attraction);
    }

    @GetMapping("/sidos/{sidoCode}")
    public ResponseEntity<?> getGugun(@PathVariable String sidoCode) {
        List<AttractionGugunDto> list = service.getGugun(sidoCode);
        return JsonResponse.okWithData(HttpStatus.OK, "attraction 구군 성공!", list);
    }

    @GetMapping("/{attractionId}")
    public ResponseEntity<?> getDetail(
            @NotBlank(message = "attractionId는 필수입니다.")
            @Pattern(regexp = "\\d+", message = "attractionId는 숫자로 이루어져야 합니다.")
            @PathVariable String attractionId) {
        if (!attractionId.matches("\\d+")) {
            return JsonResponse.fail("attractionId는 숫자로 이루어져야 합니다.", HttpStatus.BAD_REQUEST.value());
        }
        AttractionDetailResDto dto = service.getDetail(attractionId);
        return JsonResponse.okWithData(HttpStatus.OK, "attraction 상세 조회 성공", dto);
    }

    @GetMapping("/{attractionId}/evaluations")
    public ResponseEntity<?> getEvaluations(
            @NotBlank
            @Pattern(regexp = "\\d+", message = "attractionId는 숫자로 이루어져야 합니다.")
            @PathVariable String attractionId) {
        if (!attractionId.matches("\\d+")) {
            return JsonResponse.fail("attractionId는 숫자로 이루어져야 합니다.", HttpStatus.BAD_REQUEST.value());
        }
        AttractionReviewScoreDto dto = reviewService.getEvaluation(attractionId);
        return JsonResponse.okWithData(HttpStatus.OK, "attraction 평가 조회 성공", dto);
    }

    @GetMapping("/{attractionId}/reviews")
    public ResponseEntity<?> getReviewsByAttractionId(@NotBlank
                                                          @Pattern(regexp = "\\d+", message = "attractionId는 숫자로 이루어져야 합니다.")
                                                          @PathVariable String attractionId) {
        if (!attractionId.matches("\\d+")) {
            return JsonResponse.fail("attractionId는 숫자로 이루어져야 합니다.", HttpStatus.BAD_REQUEST.value());
        }
        List<AttractionReviewResDto> list = reviewService.getReviewsByAttractionId(attractionId);
        return JsonResponse.okWithData(HttpStatus.OK, "관광지 아이디로 리뷰 검색 성공", list);
    }

    @PostMapping("/{attractionId}/reviews")
    public ResponseEntity<?> postReviews(@Valid @RequestBody AttractionReviewCreateDto reviewCreateDto, @AuthenticationPrincipal SessionUser sessionUser) {
        AttractionReview review = reviewCreateDto.toEntity(sessionUser.getId());
        Long res = reviewService.writeReview(review);
        if (res != null) {
            return JsonResponse.okWithData(HttpStatus.OK, "관광지 리뷰 작성 성공", res);
        }
        return JsonResponse.fail("fail", HttpStatus.BAD_REQUEST.value());
    }

    @GetMapping("/{attractionId}/reviews/{reviewId}")
    public ResponseEntity<?> getOneReview(@NotBlank
                                              @Pattern(regexp = "\\d+", message = "reviewId는 숫자로 이루어져야 합니다.")
                                              @PathVariable String reviewId) {
        AttractionReview review = reviewService.getOneReview(reviewId);
        if (review == null) {
            return JsonResponse.fail("리뷰가 없습니다", HttpStatus.NOT_FOUND.value());
        }
        return JsonResponse.okWithData(HttpStatus.OK, "관광지 리뷰 아이디로 조회 성공", review);
    }

    @DeleteMapping("/{attractionId}/reviews/{reviewId}")
    public ResponseEntity<?> deleteOneReview(@NotBlank
                                                 @Pattern(regexp = "\\d+", message = "reviewId는 숫자로 이루어져야 합니다.")
                                                 @PathVariable String reviewId) {
        int res = reviewService.deleteReview(reviewId);
        if (res == 1) {
            return JsonResponse.okWithData(HttpStatus.OK, "관광지 리뷰 삭제 성공", res);
        }
        return JsonResponse.fail("fail", HttpStatus.NOT_FOUND.value());
    }

    @GetMapping("/reviews")
    public ResponseEntity<?> getReviewsByUserId(@AuthenticationPrincipal SessionUser sessionUser){
        List<AttractionReviewMypageDto> list = reviewService.getReviewsByUserIdForMyPage(sessionUser.getId());
        return JsonResponse.okWithData(HttpStatus.OK, "유저 아이디로 마이패이지에 넣을 리뷰 검색 성공", list);
    }
}
