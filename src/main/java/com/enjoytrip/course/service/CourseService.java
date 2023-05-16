package com.enjoytrip.course.service;

import com.enjoytrip.course.controller.dto.CourseMakeRequest;
import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseAttraction;
import com.enjoytrip.course.entity.CourseLike;

import java.util.List;

public interface CourseService {
    List<Course> SelectAll();
    List<Course> CourseByUserId(Long userId);
    List<CourseAttraction> AttractionByCourseId(Long courseId);
    void makeCourse(CourseMakeRequest makeRequest);
    void publicChange(Course course);
    void courseLike(CourseLike like);
}
