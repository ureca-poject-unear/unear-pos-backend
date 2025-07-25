package com.unear.pos.common.exception.business;

import com.unear.pos.common.exception.BusinessException;
import com.unear.pos.common.exception.ErrorCode;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException() {
        super(ErrorCode.ENTITY_NOT_FOUND);
    }

    public EntityNotFoundException(String message) {
        super(ErrorCode.ENTITY_NOT_FOUND, message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(ErrorCode.ENTITY_NOT_FOUND, message, cause);
    }

    public EntityNotFoundException(String entityType, Object id) {
        super(ErrorCode.ENTITY_NOT_FOUND, entityType + "를 찾을 수 없습니다. ID: " + id);
    }
}