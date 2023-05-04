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
    private Long courseid; // 어느 코스에 속하는지
    private Long attractionid; // 어디 갈지
    private String comment; // 남길말
    private String turn; // 순서
}
