package com.unear.pos.common.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlaceType {
    BASIC("BASIC"),
    LOCAL("LOCAL"),
    FRANCHISE("FRANCHISE");

    private final String code;

    public boolean isGeneralPolicy() {
        return this == BASIC || this == LOCAL;
    }

    public boolean isFranchisePolicy() {
        return this == FRANCHISE;
    }
}
