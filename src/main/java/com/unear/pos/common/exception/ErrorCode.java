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

    INVALID_DATA(HttpStatus.BAD_REQUEST, "M400", "유효하지 않은 데이터입니다"),

    COUPON_NOT_FOUND(HttpStatus.NOT_FOUND, "C404", "존재하지 않는 쿠폰입니다"),
    COUPON_NOT_USABLE(HttpStatus.BAD_REQUEST, "C400", "사용할 수 없는 쿠폰입니다"),
    COUPON_TEMPLATE_NOT_FOUND(HttpStatus.NOT_FOUND, "C404", "쿠폰 정보를 찾을 수 없습니다"),

    // 할인 정책 관련 에러 - D prefix
    DISCOUNT_POLICY_NOT_FOUND(HttpStatus.NOT_FOUND, "D404", "할인 정책을 찾을 수 없습니다"),
    DISCOUNT_POLICY_INVALID_STORE(HttpStatus.BAD_REQUEST, "D400", "이 가게에서 사용할 수 없는 쿠폰입니다"),
    DISCOUNT_POLICY_INVALID_FRANCHISE(HttpStatus.BAD_REQUEST, "D401", "이 프랜차이즈에서 사용할 수 없는 쿠폰입니다"),
    DISCOUNT_POLICY_NOT_APPLICABLE(HttpStatus.BAD_REQUEST, "D402", "적용할 수 없는 할인 정책입니다"),

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
