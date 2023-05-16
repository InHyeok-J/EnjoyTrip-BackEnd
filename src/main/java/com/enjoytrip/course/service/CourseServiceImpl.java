package com.enjoytrip.course.service;

import com.enjoytrip.course.controller.dto.CourseMakeRequest;
import com.enjoytrip.course.dao.CourseMapper;
import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseAttraction;
import com.enjoytrip.course.entity.CourseLike;
import com.enjoytrip.global.response.JsonResponse;
import com.enjoytrip.user.entity.User;
import com.enjoytrip.user.dao.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseMapper courseMapper;

    @Override
    public List<Course> SelectAll() {
        return courseMapper.SelectAll();
    }

    @Override
    public List<Course> CourseByUserId(Long userId) {
        return courseMapper.CourseByUserId(userId);
    }

    @Override
    public List<CourseAttraction> AttractionByCourseId(Long courseId) {
        return courseMapper.AttractionByCourseId(courseId);
    }

    @Override
    public void makeCourse(CourseMakeRequest makeRequest) {
        Course course = Course.builder()
                .userId(makeRequest.getUserId())
                .title(makeRequest.getTitle())
                .isPublic(makeRequest.getIsPublic())
                .build();
        courseMapper.makeCourse(course);

        if(course.getId() != null){
            CourseAttraction courseAttraction = CourseAttraction.builder()
                    .courseId(course.getId())
                    .build();
            for (Long attractionId : makeRequest.getAttractionIds()) {
                courseAttraction.setAttractionId(attractionId);
                courseMapper.insertAttraction(courseAttraction);
            }
        }

    }

    @Override
    public void publicChange(Course course) {
        if(courseMapper.publicChange(course) == 0){
            JsonResponse.fail("잘못된 입력입니다.", 400);
        }

        courseMapper.updatedAtChange(course.getId());
    }

    @Override
    public void courseLike(CourseLike like) {
        courseMapper.courseLike(like);
    }
}
