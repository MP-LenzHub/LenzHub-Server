package com.example.renzhubserver.fileUpload;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class FileUploadController {

    private final S3Service s3Uploader;

    @PostMapping("/upload")
    public String ImageUpload(@RequestParam(value="image") MultipartFile image) throws IOException {
        s3Uploader.upload(image);
        return "Upload OK";
    }

}
