package com.enjoytrip.course.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Course {
    private Long id;
    private Long userid; // 작성자 이메일
    private String title; // 코스명
    private boolean isPublic; // 공개 여부

    public boolean publicCheck(){
        return isPublic;
    }
}
