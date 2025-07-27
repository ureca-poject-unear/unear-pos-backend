package com.unear.pos.common.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventParticipationStatus {
    NONE("NONE", "이벤트 미참여"),
    GENERAL("GENERAL", "이벤트 참여(일반)"),
    REQUIRE("REQUIRE", "이벤트 참여(필수)");

    private final String code;
    private final String label;
}