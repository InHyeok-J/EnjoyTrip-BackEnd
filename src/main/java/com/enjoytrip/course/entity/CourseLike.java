package com.enjoytrip.course.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CourseLike {
    private Long courseId;
    private Long userId;
}
