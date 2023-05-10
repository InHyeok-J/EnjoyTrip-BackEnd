package com.enjoytrip.course.controller;

import com.enjoytrip.course.controller.dto.CourseManageRequest;
import com.enjoytrip.course.controller.dto.CoursePublicChagne;
import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseComment;
import com.enjoytrip.course.entity.CourseLike;
import com.enjoytrip.course.service.CourseService;
import com.enjoytrip.global.response.JsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/course")
@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/list")
    public ResponseEntity<?> selectAll(@RequestParam Long userId, HttpSession session){
        System.out.println(session.getId());
        List<Course> list = courseService.CourseByUserid(userId);
        return JsonResponse.okWithData(HttpStatus.OK, "MyCourse 검색 성공", list);
    }

    @PostMapping("/")
    public ResponseEntity<?> makeCourse(@RequestBody Course course){
        courseService.makeCourse(course);
        return JsonResponse.ok(HttpStatus.OK, "MakeCourse 성공");
    }
    @PostMapping("/attractions")
    public ResponseEntity<?> insertAttraction(@RequestBody CourseManageRequest manageRequest){
        Long nextTurn = courseService.nextTurn(manageRequest.getCourseId()) + 1;
        manageRequest.setTurn(nextTurn);
        System.out.println(manageRequest.toString());
        courseService.insertAttraction(manageRequest);
        return JsonResponse.okWithData(HttpStatus.OK, "Insert Course 성공",manageRequest);
    }

    @PatchMapping("/attractions")
    public ResponseEntity<?> attractionChange(@RequestBody CourseManageRequest manageRequest){
        System.out.println(manageRequest.toString());
        courseService.attractionChange(manageRequest);
        courseService.updatedAtChange(manageRequest.getCourseId());
        return JsonResponse.okWithData(HttpStatus.OK, "Course Change 성공",manageRequest);
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
