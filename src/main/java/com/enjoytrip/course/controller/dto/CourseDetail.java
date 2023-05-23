package com.enjoytrip.course.controller.dto;

import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseAttraction;
import com.enjoytrip.course.entity.CourseComment;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CourseDetail {
    private Course course;
    private String nickname;
    private String profileImg;
    private String days; // 몇박인지
    private int likeCnt;
    private int commentCnt;
    private int attractionCnt;
    private List<List<CourseAttraction>> plans;
    private List<CourseComments> comments;
}
