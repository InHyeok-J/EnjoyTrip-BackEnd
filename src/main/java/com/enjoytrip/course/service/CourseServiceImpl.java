package com.enjoytrip.course.service;

import com.enjoytrip.course.controller.dto.CourseManageRequest;
import com.enjoytrip.course.controller.dto.CoursePublicChagne;
import com.enjoytrip.course.dao.CourseMapper;
import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseAttraction;
import com.enjoytrip.course.entity.CourseComment;
import com.enjoytrip.course.entity.CourseLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseMapper courseMapper;

    @Override
    public List<Course> CourseByUserid(Long userId) {
        return courseMapper.CourseByUserid(userId);
    }

    @Override
    public void makeCourse(Course course) {
        courseMapper.makeCourse(course);
    }

    @Override
    public Long nextTurn(Long userId) {
        return courseMapper.nextTurn(userId);
    }

    @Override
    public void insertAttraction(CourseManageRequest maxTurnRequest) {
        courseMapper.insertAttraction(maxTurnRequest);
    }

    @Override
    public void attractionChange(CourseManageRequest manageRequest) {
        courseMapper.attractionChange(manageRequest);
    }

    @Override
    public void updatedAtChange(Long courseId) {
        courseMapper.updatedAtChange(courseId);
    }

    @Override
    public void publicChange(CoursePublicChagne publicChagne) {
        courseMapper.publicChange(publicChagne);
    }

    @Override
    public void commentAdd(CourseComment comment) {
        courseMapper.commentAdd(comment);
    }

    @Override
    public void courseLike(CourseLike like) {
        courseMapper.courseLike(like);
    }
}
