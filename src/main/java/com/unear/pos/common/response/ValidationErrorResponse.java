package com.unear.pos.common.response;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationErrorResponse {
    private Map<String, String> fieldErrors;

    public static ValidationErrorResponse of(Map<String, String> fieldErrors) {
        return new ValidationErrorResponse(fieldErrors);
    }
}