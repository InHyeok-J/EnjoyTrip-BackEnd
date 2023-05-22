package com.enjoytrip.global.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AwsS3Config {

    @Value("${cloud.aws.credentials.accessKey}")
    private String AWS_ACCESS_KEY;

    @Value("${cloud.aws.credentials.secretKey}")
    private String AWS_SECRETKEY_KEY;

    @Value("${cloud.aws.region}")
    private String AWS_S3_REGION;

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(
                AWS_ACCESS_KEY, AWS_SECRETKEY_KEY
            ))).withRegion(AWS_S3_REGION)
            .build();
    }
}
