package com.enjoytrip.course.controller.dto;

import com.enjoytrip.course.entity.Course;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CourseList {
    private Course course;
    private String nickname;
    private String courseExample;
    private int likeCnt;
    private int commentCnt;
}
