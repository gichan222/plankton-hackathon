package com.example.planktonhackathon.domain.board.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardWriteRequest {

    private String text;
    private MultipartFile multipartFile;
    private String district;
    private String bigCategory;
}
