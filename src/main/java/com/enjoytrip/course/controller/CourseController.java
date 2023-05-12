package com.enjoytrip.course.controller;

import com.enjoytrip.course.controller.dto.CoursePublicChagne;
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

import java.util.List;

@RequestMapping("/courses")
@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/list")
    public ResponseEntity<?> selectAll(@AuthenticationPrincipal SessionUser sessionUser){
        System.out.println(sessionUser.toString());
        List<Course> list = courseService.CourseByUserid(sessionUser.getId());
        return JsonResponse.okWithData(HttpStatus.OK, "MyCourse 검색 성공", list);
    }

    @PostMapping("/")
    public ResponseEntity<?> makeCourse(@RequestBody Course course, @AuthenticationPrincipal SessionUser sessionUser){
        course.setUserId(sessionUser.getId());
        courseService.makeCourse(course);
        return JsonResponse.ok(HttpStatus.OK, "MakeCourse 성공");
    }
    @PostMapping("/attractions")
    public ResponseEntity<?> insertAttraction(@RequestBody List<CourseAttraction> courseAttractions){
        for(int i = 0; i < courseAttractions.size(); i++){
            Long nextTurn = courseService.nextTurn(courseAttractions.get(i).getCourseId()) + 1;
            courseAttractions.get(i).setTurn(nextTurn);
            System.out.println(courseAttractions.get(i).toString());
            courseService.insertAttraction(courseAttractions.get(i));
        }
        return JsonResponse.okWithData(HttpStatus.OK, "Insert Course 성공",courseAttractions);
    }

    @PatchMapping("/attractions")
    public ResponseEntity<?> attractionChange(@RequestBody List<CourseAttraction> courseAttractions){
        for(int i = 0; i < courseAttractions.size(); i++){
            System.out.println(courseAttractions.get(i).toString());
            courseService.attractionChange(courseAttractions.get(i));
            courseService.updatedAtChange(courseAttractions.get(i).getCourseId());
        }
        return JsonResponse.okWithData(HttpStatus.OK, "Course Change 성공",courseAttractions);
    }

    @PatchMapping("/public")
    public ResponseEntity<?> publicChange(@RequestBody CoursePublicChagne publicChagne){
        courseService.publicChange(publicChagne);
        return JsonResponse.okWithData(HttpStatus.OK, "Public Change 성공",publicChagne);
    }

    @PostMapping("/comment")
    public ResponseEntity<?> commentAdd(@RequestBody CourseComment comment){
        courseService.commentAdd(comment);
        return JsonResponse.ok(HttpStatus.OK, "Comment Add 성공");
    }
    @PostMapping("/like")
    public ResponseEntity<?> courseLike(@RequestBody CourseLike like){
        courseService.courseLike(like);
        return JsonResponse.ok(HttpStatus.OK, "Comment Like 성공");
    }
}
