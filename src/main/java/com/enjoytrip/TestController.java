package com.enjoytrip;


import com.enjoytrip.global.response.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String hello(){
        return "test";
    }

    @GetMapping("/test/json")
    public ResponseEntity<?> testJson(){
        return JsonResponse.ok(HttpStatus.OK,"test 응답");
    }
}
