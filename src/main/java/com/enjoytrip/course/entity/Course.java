package com.enjoytrip.course.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Course {
    private Long id;
    private Long userId; // 작성자 id
    private String title; // 코스명
    private Boolean isPublic; // 공개 여부
    private LocalDateTime createdAt; //생성 날짜
    private LocalDateTime updatedAt; // 수정 날짜
}
