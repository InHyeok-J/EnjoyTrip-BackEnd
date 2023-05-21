package com.enjoytrip.global.common;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.enjoytrip.global.exception.BusinessException;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Uploader {

    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.url}")
    private String URL_PREFIX;

    @Value("${cloud.aws.s3.bucket}")
    private String S3_BUCKET;


    @Value("${cloud.aws.s3.dir}")
    private String S3_DIR;

    public String uploadFile(MultipartFile multipartFile) {
        String fileName = createFileName(multipartFile.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());
        try {
            InputStream inputStream = multipartFile.getInputStream();
            amazonS3.putObject(
                new PutObjectRequest(S3_BUCKET, fileName, inputStream, objectMetadata));
            return URL_PREFIX + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("이미지 업로드 실패", 500);
        }
    }

    private String createFileName(String fileName) {
        String convertFilename = Base64.getUrlEncoder().encodeToString(fileName.getBytes());
        return S3_DIR + UUID.randomUUID().toString().concat(convertFilename);
    }
}
