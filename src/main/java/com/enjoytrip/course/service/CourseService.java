package com.enjoytrip.course.service;

import com.enjoytrip.course.controller.dto.CourseManageRequest;
import com.enjoytrip.course.controller.dto.CoursePublicChagne;
import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseAttraction;
import com.enjoytrip.course.entity.CourseComment;
import com.enjoytrip.course.entity.CourseLike;

import java.util.List;

public interface CourseService {
    List<Course> CourseByUser(Long userId);
    void makeCourse(Course course);
    Long nextTurn(Long userId);
    void insertCourse(CourseManageRequest maxTurnRequest);
    void courseChange(CourseManageRequest manageRequest);
    void updatedAtChange(Long courseId);
    void publicChange(CoursePublicChagne publicChagne);
    void commentAdd(CourseComment comment);
    void courseLike(CourseLike like);
}
