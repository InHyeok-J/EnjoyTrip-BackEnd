package com.enjoytrip.course.service;

import com.enjoytrip.course.controller.dto.CourseComments;
import com.enjoytrip.course.controller.dto.CourseDetail;
import com.enjoytrip.course.controller.dto.CourseList;
import com.enjoytrip.course.controller.dto.CourseMakeRequest;
import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseAttraction;
import com.enjoytrip.course.entity.CourseComment;
import com.enjoytrip.course.entity.CourseLike;
import com.enjoytrip.global.dto.SessionUser;

import java.util.List;

public interface CourseService {
    List<CourseList> SelectAll();
    CourseDetail SelectOneByCourseId(Long courseId, SessionUser sessionUser);
    List<Course> CourseByUserId(Long userId);
    List<CourseAttraction> AttractionByCourseId(Long courseId);
    void makeCourse(CourseMakeRequest makeRequest);
    int publicChange(Course course);
    void courseLike(CourseLike like);
    CourseComments commentAdd(CourseComment courseComment);
    boolean likeChange(CourseLike courseLike);
}
