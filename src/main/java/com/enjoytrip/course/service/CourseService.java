package com.enjoytrip.course.service;

import com.enjoytrip.course.controller.dto.CoursePublicChagne;
import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseAttraction;
import com.enjoytrip.course.entity.CourseComment;
import com.enjoytrip.course.entity.CourseLike;

import java.util.List;

public interface CourseService {
    List<Course> CourseByUserid(Long userId);
    void makeCourse(Course course);
    Long nextTurn(Long userId);
    void insertAttraction(CourseAttraction courseAttraction);
    void attractionChange(CourseAttraction courseAttraction);
    void updatedAtChange(Long courseId);
    void publicChange(CoursePublicChagne publicChagne);
    void commentAdd(CourseComment comment);
    void courseLike(CourseLike like);
}
