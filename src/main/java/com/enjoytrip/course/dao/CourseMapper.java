package com.enjoytrip.course.dao;

import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseAttraction;
import com.enjoytrip.course.entity.CourseComment;
import com.enjoytrip.course.entity.CourseLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    List<Course> SelectAll();
    Course SelectOneByCourseId(Long courseId);
    List<Course> CourseByUserId(Long userId);
    List<CourseAttraction> AttractionByCourseId(Long courseId);
    List<CourseAttraction> GetCourseExample(Long courseId);
    int likeCnt(Long courseId);
    int commentCnt(Long courseId);
    void makeCourse(Course course);
    void insertAttraction(CourseAttraction courseAttraction);
    int publicChange(Course course);
    void courseLike(CourseLike like);
    void commentAdd(CourseComment courseComment);
    List<CourseComment> commentsByCourseId(Long courseId);
    boolean likeCheckByCourseIdUserId(CourseLike courseLike);
    int likeChange(CourseLike courseLike);
    CourseComment commentByCommentId(Long id);
}
