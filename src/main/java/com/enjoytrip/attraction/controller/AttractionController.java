package com.enjoytrip.attraction.controller;

import com.enjoytrip.attraction.dto.AttractionSearchOptions;
import com.enjoytrip.attraction.entity.Attraction;
import com.enjoytrip.attraction.service.AttractionService;
import com.enjoytrip.global.response.JsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService service;
    @GetMapping("/attractions")
    public ResponseEntity<?> search(AttractionSearchOptions options) {
        List<Attraction> list = service.search(options);
        return JsonResponse.okWithData(HttpStatus.OK, "attraction 검색 성공!", list);
    }
}
