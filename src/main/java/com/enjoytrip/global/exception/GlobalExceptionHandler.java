package com.enjoytrip.global.exception;


import com.enjoytrip.global.response.JsonResponse;
import java.io.IOException;
import javax.lang.model.type.ErrorType;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

//    @ExceptionHandler(BusinessException.class) // business Exception
//    public ResponseEntity<?> handlingBusinessException(BusinessException e){
//        log.warn(e.getMessage());
//        return JsonResponse.fail(e.getMessage(), e.statusCode);
//    }
//
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) throws IOException {
//        // 검증 실패시 발생.
//        return JsonResponse.fail( "잘못된 값입니다.", HttpStatus.BAD_REQUEST.value());
//    }
//    // HttpMessageNotReadableException -> body의 타입이 잘못됨
//    // HttpRequestMethodNotSupportedException-> method 지원 x
//    @ExceptionHandler(value = {HttpMessageNotReadableException.class,
//        HttpRequestMethodNotSupportedException.class})
//    public ResponseEntity<?> handleDateTimeFormatException(HttpMessageNotReadableException e) throws IOException {
//        return JsonResponse.fail( "잘못된 요청입니다.", HttpStatus.BAD_REQUEST.value());
//    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> internalServerException(Exception e) throws IOException {
//        return JsonResponse.fail( "서버 에러",HttpStatus.INTERNAL_SERVER_ERROR.value());
//    }



}
