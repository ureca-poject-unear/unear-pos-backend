package com.unear.pos.common.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlaceCategory {
    FOOD("FOOD", "푸드"),
    ACTIVITY("ACTIVITY", "액티비티"),
    EDUCATION("EDUCATION", "교육"),
    CULTURE("CULTURE", "문화/여가"),
    BAKERY("BAKERY", "베이커리"),
    LIFE("LIFE", "생활/편의"),
    SHOPPING("SHOPPING", "쇼핑"),
    CAFE("CAFE", "카페"),
    BEAUTY("BEAUTY", "뷰티/건강");

    private final String code;
    private final String label;
}
