package com.enjoytrip.course.controller.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CoursePublicChagne {
    private Long id;
    private Boolean isPublic;
}
