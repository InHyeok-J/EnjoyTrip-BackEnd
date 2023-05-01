package com.enjoytrip.global.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JsonResponse {

    public static ResponseEntity<?> ok(HttpStatus status, String meesage){
        SuccessResponse response = new SuccessResponse(true, meesage, null);
        return ResponseEntity.status(status.value())
            .body(response);
    }

    public static ResponseEntity<?> okWithData(HttpStatus status, String message, Object data) {
        SuccessResponse response = new SuccessResponse(true, message, data);
        return ResponseEntity.status(status.value())
            .body(response);
    }

    public static ResponseEntity<?> fail(String message) {
        FailResponse response = new FailResponse(false, message, 400);
        return ResponseEntity.status(400)
            .body(response);
    }

}
