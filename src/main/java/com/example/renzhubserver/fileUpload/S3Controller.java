package com.example.renzhubserver.fileUpload;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class S3Controller {

    private final S3Service s3Service;

    @PostMapping("/upload")
    public String ImageUpload(@RequestParam(value="image") MultipartFile image) throws IOException {
        s3Service.upload(image);
        return "Upload OK";
    }

    @PostMapping("/delete/{fileName}")
    public String imageDelete(@PathVariable("fileName") String fileName) {
        s3Service.delete(fileName);
        return "Delete OK";
    }
}
