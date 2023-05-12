package com.enjoytrip.course.controller.dto;

import com.enjoytrip.course.entity.CourseAttraction;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CourseMakeRequest {
    private String title; // course 제목
    private Boolean isPublid; // 게시글 공개여부
    private List<CourseAttraction> tmp;
    /*
    * {
    * title: "courseA"
    * isPunblic : True,
    * tmp:[{
    * attationId : 1,
    * comment : "밥"
    * }]
    * }
    * */
}
