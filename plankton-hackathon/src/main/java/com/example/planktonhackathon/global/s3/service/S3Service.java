package com.example.planktonhackathon.global.s3.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

    public String uploadFile(MultipartFile file, String nickName) throws IOException {
        // 파일명 생성
        String fileName = UUID.randomUUID().toString();

        // S3에 업로드할 파일 URL 생성 (한 번 인코딩된 이메일 아래에 파일명이 들어가도록)
        String fileUrl = "https://" + bucket + ".s3.ap-northeast-2.amazonaws.com/" + nickName + "/" + fileName;

        // 파일 경로는 한 번 인코딩된 이메일 폴더 아래 파일명으로 설정
        String s3FilePath = nickName + "/" + fileName;

        // 메타데이터 설정
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        // 파일을 S3에 업로드
        amazonS3Client.putObject(bucket, s3FilePath, file.getInputStream(), metadata);

        // 업로드된 파일 URL 반환
        return fileUrl;
    }
}
