package com.enjoytrip.course.service;

import com.enjoytrip.course.dao.CourseMapper;
import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseAttraction;
import com.enjoytrip.course.entity.CourseComment;
import com.enjoytrip.course.entity.CourseLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseMapper courseMapper;
    @Override
    public ArrayList<Course> selectAllMyCourse(Long userid) {
        return courseMapper.selectAllMyCourse(userid);
    }

    @Override
    public void makeCourse(Course course) {
        courseMapper.makeCourse(course);
    }

    @Override
    public Long nextTurn(Course course) {
        return courseMapper.nextTurn(course);
    }

    @Override
    public void insertCourse(CourseAttraction attraction) {
        courseMapper.insertCourse(attraction);
    }

    @Override
    public void courseChange(CourseAttraction attraction) {
        courseMapper.courseChange(attraction);
    }

    @Override
    public void updateAtChange(Course course) {
        courseMapper.updateAtChange(course);
    }

    @Override
    public void publicChange(Course course) {
        courseMapper.publicChange(course);
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
