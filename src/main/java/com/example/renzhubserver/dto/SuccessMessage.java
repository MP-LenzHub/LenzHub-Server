package com.example.renzhubserver.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Getter
public class SuccessMessage {
//    SUCCESS(OK, true, "요청에 성공하였습니다.");
    private final int code;
    private final boolean isSuccess;
    private final String message;

    SuccessMessage(HttpStatus code, boolean isSuccess, String message) {
        this.code = code.value();
        this.isSuccess = isSuccess;
        this.message = message;
    }
    public static final SuccessMessage SUCCESS = new SuccessMessage(OK, true, "요청에 성공하였습니다.");
}
