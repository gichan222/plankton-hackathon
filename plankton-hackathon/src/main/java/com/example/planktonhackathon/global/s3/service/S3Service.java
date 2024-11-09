package com.example.planktonhackathon.global.s3.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadFile(MultipartFile file, String email) throws IOException {
        // 파일명 가져오기
        String fileName = String.valueOf(UUID.randomUUID());

        // S3에 업로드할 파일 URL 생성
        String fileUrl = "https://" + bucket + "/" + email + "/" + fileName;

        // 메타데이터 설정
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        // 파일을 S3에 업로드
        amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);

        // 업로드된 파일 URL 반환
        return fileUrl;
    }
}