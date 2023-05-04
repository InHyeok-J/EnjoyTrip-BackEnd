package com.enjoytrip.course.controller;

import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseAttraction;
import com.enjoytrip.course.entity.CourseComment;
import com.enjoytrip.course.entity.CourseLike;
import com.enjoytrip.course.service.CourseService;
import com.enjoytrip.global.response.JsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.xml.stream.events.Comment;
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
    public ResponseEntity<?> makeCourse(@RequestBody Course course){
        courseService.makeCourse(course);
        return JsonResponse.ok(HttpStatus.OK, "MakeCourse 성공");
    }
    @PostMapping("/insert")
    public ResponseEntity<?> insertCourse(@RequestBody CourseAttraction courseAttraction, @RequestBody Course course){
        courseAttraction.setCourseid(courseService.nextTurn(course));
        courseService.insertCourse(courseAttraction);
        return JsonResponse.ok(HttpStatus.OK, "Insert Course 성공");
    }

    @PutMapping("/change")
    public ResponseEntity<?> courseChange(@RequestBody CourseAttraction courseAttraction, @RequestBody Course course){
        courseService.courseChange(courseAttraction);
        courseService.updateAtChange(course);
        return JsonResponse.ok(HttpStatus.OK, "Course Change 성공");
    }

    @PostMapping("/public")
    public ResponseEntity<?> publicChange(boolean pub, HttpSession session){
        Course course = (Course) session.getAttribute("course");
        course.setPublic(pub);
        courseService.publicChange(course);
        return JsonResponse.ok(HttpStatus.OK, "Public Change 성공");
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
