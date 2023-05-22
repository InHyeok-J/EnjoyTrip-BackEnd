package com.enjoytrip.course.controller.dto;

import com.enjoytrip.course.entity.Course;
import com.enjoytrip.course.entity.CourseAttraction;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CourseMakeRequest {
    private Long userId;
    private String title;
    private Boolean isPublic;
    private List<Long> attractionIds;

    /*
    * {
    * title : "코스1",
    * isPublic : "true",
    * attractionIds:[{
    * atractionId : 125266
    * }]
    * }
    * */
}
