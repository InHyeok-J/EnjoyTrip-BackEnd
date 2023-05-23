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
    private int turn; // 순선
    private String attractionName; // 관광지 이름
    private String address; // 관광지 주소
    private String attractionImageUrl; // 관광지 사진
    private String latitude; //위도
    private String longitude; //경도
    private int day; // 몇번째 날인지
    
    
}
