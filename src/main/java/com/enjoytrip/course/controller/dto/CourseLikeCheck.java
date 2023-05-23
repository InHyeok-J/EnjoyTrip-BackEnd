package com.enjoytrip.course.controller.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CourseLikeCheck {
    private Long userId;
    private Long courseId;
}
