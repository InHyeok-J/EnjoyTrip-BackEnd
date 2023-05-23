package com.enjoytrip.course.service;

import com.enjoytrip.course.controller.dto.CourseComments;
import com.enjoytrip.course.controller.dto.CourseDetail;
import com.enjoytrip.course.controller.dto.CourseList;
import com.enjoytrip.course.controller.dto.CourseMakeRequest;
import com.enjoytrip.course.dao.CourseMapper;
import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseAttraction;
import com.enjoytrip.course.entity.CourseComment;
import com.enjoytrip.course.entity.CourseLike;
import com.enjoytrip.global.response.JsonResponse;
import com.enjoytrip.user.entity.User;
import com.enjoytrip.user.dao.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseMapper courseMapper;
    private final UserMapper userMapper;

    @Override
    public List<CourseList> SelectAll() {
        List<CourseList> list = new ArrayList<>();
        List<Course> courseList = courseMapper.SelectAll();

        for (Course course: courseList) {
            User user = userMapper.selectNicknameProfileByCourseId(course.getUserId());
            String nickname = user.getNickname();
            StringBuilder courseExample = new StringBuilder();
            List<CourseAttraction> courseAttraction = courseMapper.GetCourseExample(course.getId());

            int attractionsSize = courseAttraction.size()>=4?4:courseAttraction.size();
            for (int i = 0; i < attractionsSize; i++) {
                courseExample.append(courseAttraction.get(i).getAttractionName()+", ");
            }

            int likeCnt = courseMapper.likeCnt(course.getId());
            int commentCnt = courseMapper.commentCnt(course.getId());
            courseExample.replace(courseExample.length()-3,courseExample.length(),"");
            list.add(new CourseList(course,nickname,courseExample.toString(),likeCnt,commentCnt));
        }
        return list;
    }

    @Override
    public CourseDetail SelectOneByCourseId(Long id) {
        CourseDetail courseDetail = null;
        Course course = courseMapper.SelectOneByCourseId(id);
        User user = userMapper.selectNicknameProfileByCourseId(course.getUserId());
        String nickname = user.getNickname();
        String profileImg = user.getProfileImg();
        int likeCnt = courseMapper.likeCnt(course.getId());
        int commentCnt = courseMapper.commentCnt(course.getId());
        String days = "";
        if(course.getSchedule() == 1){
            days = "당일치기";
        }else if(course.getSchedule() == 2){
            days = "1박 2일";
        }else if(course.getSchedule() == 3){
            days = "2박 3일";
        }else if(course.getSchedule() == 4){
            days = "3박 4일";
        }

        List<List<CourseAttraction>> plans = new ArrayList<>();
        for (int i = 0; i < course.getSchedule(); i++) {
            plans.add(new ArrayList<>());
        }

        List<CourseAttraction> courseAttractions = courseMapper.AttractionByCourseId(course.getId());
        int attractionCnt = 0;
        for (CourseAttraction courseAttraction: courseAttractions) {
            plans.get(courseAttraction.getDay() -1).add(courseAttraction);
            attractionCnt++;
        }

        List<CourseComments> comments = courseMapper.commentsByCourseId(course.getId());
//        for (int i = 0; i < comments.size(); i++ ) {
////            User u = userMapper.selectNicknameProfileByCourseId(comments.get(i).getCourseComment().getCourseId());
////            comments.get(i).setNickname(u.getNickname());
//        }


        courseDetail = new CourseDetail(course, nickname,profileImg, days,likeCnt,commentCnt,attractionCnt,plans,comments);
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
    public int publicChange(Course course) {
        return courseMapper.publicChange(course);
    }

    @Override
    public void courseLike(CourseLike like) {
        courseMapper.courseLike(like);
    }

    @Override
    public void commentAdd(CourseComment courseComment) {
        courseMapper.commentAdd(courseComment);
    }
}
