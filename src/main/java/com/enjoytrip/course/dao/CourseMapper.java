package com.enjoytrip.course.dao;

import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseAttraction;
import com.enjoytrip.course.entity.CourseLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    List<Course> SelectAll();
    List<Course> CourseByUserId(Long userId);
    List<CourseAttraction> AttractionByCourseId(Long courseId);
    void makeCourse(Course course);
    void insertAttraction(CourseAttraction courseAttraction);
    int publicChange(Course course);
    void updatedAtChange(Long courseId);
    void courseLike(CourseLike like);
}
