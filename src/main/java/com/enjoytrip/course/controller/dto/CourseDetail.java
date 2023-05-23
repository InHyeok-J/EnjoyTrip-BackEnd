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
    private int likeCnt; // 좋아요 개수
    private int commentCnt; // 댓글 개수
    private int attractionCnt; // 관광지 개수
    private List<List<CourseAttraction>> plans; // 날짜별로 어트렉션
    private List<CourseComments> comments; // 댓글
    private Boolean isLike;


}
