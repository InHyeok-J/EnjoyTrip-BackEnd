package com.enjoytrip.course.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CourseComment {
    private Long id;
    private Long courseid;
    private Long userid;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
