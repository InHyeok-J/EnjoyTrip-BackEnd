package com.enjoytrip.course.controller.dto;

import com.enjoytrip.course.entity.CourseComment;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CourseComments {
    private CourseComment courseComment;
    private String nickname;
    private String profileImgUrl;
}
