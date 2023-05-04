package com.enjoytrip.course.service;

import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseAttraction;
import com.enjoytrip.course.entity.CourseComment;
import com.enjoytrip.course.entity.CourseLike;

import java.util.ArrayList;

public interface CourseService {
    ArrayList<Course> selectAllMyCourse(Long userid);
    void makeCourse(CourseAttraction attraction);
    void insertCourse(CourseAttraction attraction);
    void courseChange(CourseAttraction attraction);
    void updateAtChange(Course course);
    void publicChange(Course course);
    void commentAdd(CourseComment comment);
    void courseLike(CourseLike like);
}
