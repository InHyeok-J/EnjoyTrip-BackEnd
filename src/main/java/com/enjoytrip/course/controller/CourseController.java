package com.enjoytrip.course.controller;

import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseAttraction;
import com.enjoytrip.course.service.CourseService;
import com.enjoytrip.global.response.JsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

@RequestMapping("/course")
@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("")
    public ResponseEntity<?> selectAll(@RequestBody Long id){
        List<Course> list = courseService.selectAllMyCourse(id);
        return JsonResponse.okWithData(HttpStatus.OK, "MyCourse 검색 성공", list);
    }

    @PostMapping("/make")
    public ResponseEntity<?> makeCourse(@RequestBody CourseAttraction courseAttraction){
        courseService.makeCourse(courseAttraction);
        return JsonResponse.ok(HttpStatus.OK, "MakeCourse 성공");
    }

}
