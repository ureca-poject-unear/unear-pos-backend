package com.unear.pos.common.dto;

import com.unear.pos.owner.entity.Owner;
import com.unear.pos.place.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PosSessionInfo {
    private Long ownerId;
    private String ownerName;
    private Long posId;
    private Long placeId;
    private Long franchiseId;
    private String placeName;
    private String placeDesc;
    private String address;
    private String markerCode;
    private String eventTypeCode;
    private String categoryCode;
    private String benefitCategory;
    private String tel;
    private Integer startTime;
    private Integer endTime;

    public static PosSessionInfo from(Owner owner, Place place) {
        return new PosSessionInfo(
                owner.getOwnerId(), owner.getOwnerName(), owner.getPosId(),
                place.getPlaceId(), place.getFranchiseId(), place.getPlaceName(),
                place.getPlaceDesc(), place.getAddress(), place.getMarkerCode(),
                place.getEventTypeCode(), place.getCategoryCode(),
                place.getBenefitCategory(), place.getTel(), place.getStartTime(), place.getEndTime()
        );
    }
}
