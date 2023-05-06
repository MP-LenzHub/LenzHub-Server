package com.example.renzhubserver.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 200, "요청에 성공하였습니다."),
    // users
    USERS_EMPTY(false, 3000, "아이디 혹은 비밀번호를 확인해주세요."),
    USERS_EXIST_USER_ID(false, 3010, "이미 존재하는 아이디 입니다."),
    USER_DELETE(false, 3020, "유저 삭제에 실패하였습니다.");


    private final boolean isSuccess;
    private final int code;
    private final String message;
    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
