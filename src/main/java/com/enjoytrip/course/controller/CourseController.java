package com.enjoytrip.course.controller;

import com.enjoytrip.course.controller.dto.CourseDetail;
import com.enjoytrip.course.controller.dto.CourseList;
import com.enjoytrip.course.controller.dto.CourseMakeRequest;
import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseAttraction;
import com.enjoytrip.course.entity.CourseComment;
import com.enjoytrip.course.entity.CourseLike;
import com.enjoytrip.course.service.CourseService;
import com.enjoytrip.global.dto.SessionUser;
import com.enjoytrip.global.response.JsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/courses")
@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("")
    public ResponseEntity<?> selectAll(){
        List<CourseList> list = courseService.SelectAll();
        return JsonResponse.okWithData(HttpStatus.OK, "ALL Course 검색 성공", list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> selectOne(@PathVariable Long id){
        CourseDetail courseDetail = courseService.SelectOneByCourseId(id);
        return JsonResponse.okWithData(HttpStatus.OK, "Course Detail 검색 성공", courseDetail);
    }

    @GetMapping("/my-list")
    public ResponseEntity<?> courseByUserId(@AuthenticationPrincipal SessionUser sessionUser){
        List<Course> list = courseService.CourseByUserId(sessionUser.getId());
        return JsonResponse.okWithData(HttpStatus.OK, "My Course 검색 성공", list);
    }

    @PostMapping("/")
    public ResponseEntity<?> makeCourse(@RequestBody CourseMakeRequest makeRequest, @AuthenticationPrincipal SessionUser sessionUser){
        if(makeRequest.getAttractionIds().size() == 0){
            return JsonResponse.fail("Attraction 을 최소한 한 개 이상 선택해주세요.", 400);
        }
        makeRequest.setUserId(sessionUser.getId());
        courseService.makeCourse(makeRequest);
        return JsonResponse.ok(HttpStatus.OK, "MakeCourse 성공");
    }

    @GetMapping("/attractions")
    public ResponseEntity<?> attractionByCourseId(Long courseId){
        List<CourseAttraction> list =courseService.AttractionByCourseId(courseId);
        return JsonResponse.okWithData(HttpStatus.OK, "MyCourse 검색 성공", list);
    }

    @PatchMapping("/public")
    public ResponseEntity<?> publicChange(@RequestBody Course course){
        if(courseService.publicChange(course) == 0){
            return JsonResponse.fail("잘못된 입력입니다.", 400);
        }
        return JsonResponse.okWithData(HttpStatus.OK, "Public Change 성공",course);
    }
    @PostMapping("/like")
    public ResponseEntity<?> courseLike(@RequestBody CourseLike like, @AuthenticationPrincipal SessionUser sessionUser){
        like.setUserId(sessionUser.getId());
        courseService.courseLike(like);
        return JsonResponse.ok(HttpStatus.OK, "Comment Like 성공");
    }

    @PostMapping("/comment")
    public ResponseEntity<?> courseComment(@RequestBody CourseComment courseComment, @AuthenticationPrincipal SessionUser sessionUser){
        courseComment.setUserId(sessionUser.getId());
        courseService.commentAdd(courseComment);
        return JsonResponse.ok(HttpStatus.OK, "Comment Like 성공");
    }
}
