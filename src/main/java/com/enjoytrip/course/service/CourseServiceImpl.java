package com.enjoytrip.course.service;

import com.enjoytrip.attraction.dao.AttractionReviewMapper;
import com.enjoytrip.attraction.entity.AttractionReview;
import com.enjoytrip.course.controller.dto.*;
import com.enjoytrip.course.dao.CourseMapper;
import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseAttraction;
import com.enjoytrip.course.entity.CourseComment;
import com.enjoytrip.course.entity.CourseLike;
import com.enjoytrip.global.dto.SessionUser;
import com.enjoytrip.global.response.JsonResponse;
import com.enjoytrip.user.entity.User;
import com.enjoytrip.user.dao.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;
    private final UserMapper userMapper;

    @Override
    public List<CourseList> SelectAll() {
        List<CourseList> list = new ArrayList<>();
        List<Course> courseList = courseMapper.SelectAll();

        for (Course course : courseList) {
            User user = userMapper.selectNicknameProfileByUserId(course.getUserId());
            String nickname = user.getNickname();
            StringBuilder courseExample = new StringBuilder();
            List<CourseAttraction> courseAttraction = courseMapper.GetCourseExample(course.getId());

            int attractionsSize = courseAttraction.size() >= 4 ? 4 : courseAttraction.size();
            for (int i = 0; i < attractionsSize; i++) {
                courseExample.append(courseAttraction.get(i).getAttractionName() + ", ");
            }

            int likeCnt = courseMapper.likeCnt(course.getId());
            int commentCnt = courseMapper.commentCnt(course.getId());
//            courseExample.replace(courseExample.length()-2,courseExample.length(),"");
            list.add(
                new CourseList(course, nickname, courseExample.toString(), likeCnt, commentCnt));
        }
        return list;
    }

    @Override
    public List<CourseList> coursesByLike(Long userId) {
        List<CourseList> list = new ArrayList<>();
        List<Course> courseList = courseMapper.coursesByLike(userId);

        for (Course course : courseList) {
            User user = userMapper.selectNicknameProfileByUserId(course.getUserId());
            String nickname = user.getNickname();
            StringBuilder courseExample = new StringBuilder();
            List<CourseAttraction> courseAttraction = courseMapper.GetCourseExample(course.getId());

            int attractionsSize = courseAttraction.size() >= 4 ? 4 : courseAttraction.size();
            for (int i = 0; i < attractionsSize; i++) {
                courseExample.append(courseAttraction.get(i).getAttractionName() + ", ");
            }

            int likeCnt = courseMapper.likeCnt(course.getId());
            int commentCnt = courseMapper.commentCnt(course.getId());
            courseExample.replace(courseExample.length() - 3, courseExample.length(), "");
            list.add(
                new CourseList(course, nickname, courseExample.toString(), likeCnt, commentCnt));
        }
        return list;
    }

    @Override
    public CourseDetail SelectOneByCourseId(Long courseId, SessionUser sessionUser) {
        CourseDetail courseDetail = null;
        Course course = courseMapper.SelectOneByCourseId(courseId);
        User user = userMapper.selectNicknameProfileByUserId(course.getUserId());
        String nickname = user.getNickname();
        String profileImg = user.getProfileImg();
        int likeCnt = courseMapper.likeCnt(course.getId());
        int commentCnt = courseMapper.commentCnt(course.getId());
        String days = "";
        if (course.getSchedule() == 1) {
            days = "당일치기";
        } else if (course.getSchedule() == 2) {
            days = "1박 2일";
        } else if (course.getSchedule() == 3) {
            days = "2박 3일";
        } else if (course.getSchedule() == 4) {
            days = "3박 4일";
        }

        List<List<CourseAttraction>> plans = new ArrayList<>();
        for (int i = 0; i < course.getSchedule(); i++) {
            plans.add(new ArrayList<>());
        }

        List<CourseAttraction> courseAttractions = courseMapper.AttractionByCourseId(
            course.getId());
        int attractionCnt = 0;
        for (CourseAttraction courseAttraction : courseAttractions) {
            plans.get(courseAttraction.getDay() - 1).add(courseAttraction);
            attractionCnt++;
        }

        List<CourseComment> comment = courseMapper.commentsByCourseId(course.getId());
        List<CourseComments> comments = new ArrayList<>();
        for (CourseComment cc : comment) {
            User u = userMapper.selectNicknameProfileByUserId(cc.getUserId());
            comments.add(new CourseComments(cc, u.getNickname(), u.getProfileImg()));
        }

        Boolean isLike = false;
        if (sessionUser != null) {
            CourseLike courseLike = new CourseLike(courseId, sessionUser.getId(), false);
            isLike = courseMapper.likeCheckByCourseIdUserId(courseLike) == null ? false
                : courseMapper.likeCheckByCourseIdUserId(courseLike);
        }

        courseDetail = new CourseDetail(course, nickname, profileImg, days, likeCnt, commentCnt,
            attractionCnt, plans, comments, isLike);
        return courseDetail;
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
    public void makeCourse(CourseMakeRequest makeRequest, Long userId) {
        Course course = Course.builder()
            .userId(userId)
            .title(makeRequest.getTitle())
            .schedule(makeRequest.getSchedule())
            .description(makeRequest.getDescription())
            .isPublic(makeRequest.getIsPublic())
            .courseImgUrl(makeRequest.getImage())
            .build();

        courseMapper.makeCourse(course);

        if (course.getId() != null) {
            CourseAttraction courseAttraction = CourseAttraction.builder()
                .courseId(course.getId())
                .build();
            for (CourseAttractionRequest attraction : makeRequest.getAttractions()) {
                courseAttraction.setAttractionId(attraction.getAttractionId());
                courseAttraction.setDay(attraction.getDay());
                courseAttraction.setDate(attraction.getDate());
                courseMapper.insertAttraction(courseAttraction);
            }
        }

    }

    @Override
    public int publicChange(Course course) {
        return courseMapper.publicChange(course);
    }

    @Override
    public void courseLike(CourseLike like) {
        courseMapper.courseLike(like);
    }

    @Override
    public CourseComments commentAdd(CourseComment courseComment) {
        courseMapper.commentAdd(courseComment);
        if (courseComment.getId() > 0) {
            User user = userMapper.selectNicknameProfileByUserId(courseComment.getUserId());
            String nickname = user.getNickname();
            String profileImgUrl = user.getProfileImg();
            courseComment = courseMapper.commentByCommentId(courseComment.getId());
            CourseComments courseComments = new CourseComments(courseComment, nickname,
                profileImgUrl);
            return courseComments;
        } else {
            return null;
        }
    }

    @Override
    public boolean likeChange(CourseLike courseLike) {
        int flag = courseMapper.likeCheckThisCourse(courseLike);
        if (flag == 0) {
            courseMapper.courseLike(courseLike);
            return true;
        }
        return courseMapper.likeChange(courseLike) == 1 ? !courseLike.getIsLike()
            : courseLike.getIsLike();
    }

    @Override
    public List<CourseComment> commentByUserId(Long userId) {
        return courseMapper.commentByUserId(userId);
    }
}
