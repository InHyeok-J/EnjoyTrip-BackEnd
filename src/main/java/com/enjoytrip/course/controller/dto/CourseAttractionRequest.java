package com.enjoytrip.course.controller.dto;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CourseAttractionRequest {

    private Long attractionId;
    private int day;
    private LocalDateTime date;
}
