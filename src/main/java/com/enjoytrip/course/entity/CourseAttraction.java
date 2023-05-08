package com.enjoytrip.course.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CourseAttraction {
    private Long id;
    private Long courseId; // 어느 코스에 속하는지
    private Long attractionId; // 어디 갈지
    private String comment; // 남길말
    private Long turn; // 순서
}
