package com.unear.pos.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    //common
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C500", "서버 내부 오류가 발생했습니다"),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C400", "요청 값이 유효하지 않습니다"),

    // business
    OWNER_NOT_FOUND(HttpStatus.NOT_FOUND, "U404", "사용자를 찾을 수 없습니다."),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "E404", "엔티티를 찾을 수 없습니다"),


    // membership - M prefix 사용
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "M404", "회원을 찾을 수 없습니다"),
    INVALID_VERIFICATION_TYPE(HttpStatus.BAD_REQUEST, "M400", "유효하지 않은 인증 타입입니다");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
