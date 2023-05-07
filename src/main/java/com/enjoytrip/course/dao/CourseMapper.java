package com.enjoytrip.course.dao;

import com.enjoytrip.course.controller.dto.CourseManageRequest;
import com.enjoytrip.course.controller.dto.CoursePublicChagne;
import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseAttraction;
import com.enjoytrip.course.entity.CourseComment;
import com.enjoytrip.course.entity.CourseLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    // 1. 내가 만든 코스 보기
    List<Course> selectAll();
    List<Course> CourseByUser(Long userId);
    Course selectOne(Course course);
    // 2. 코스 만들기
    // 2-1) 여행 코스 추가 insert
    // 2-2) 여행지 수장(관광지, 코멘트, 순서) 변경 update
    void makeCourse(Course course);
    Long nextTurn(Long userId);
    void insertCourse(CourseManageRequest manageRequest);
    void courseChange(CourseManageRequest manageRequest);
    void updatedAtChange(Long courseId);

    // 3. 코스 공개 여부 바꾸기
    void publicChange(CoursePublicChagne publicChagne);

    // 4. 코스 댓글 달기 (200자 제한)
    void commentAdd(CourseComment comment);

    // 5. 코스 좋아요
    void courseLike(CourseLike like);
}
