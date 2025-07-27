package com.unear.pos.common.dto;

import com.unear.pos.common.dto.enums.EventParticipationStatus;
import com.unear.pos.common.dto.enums.PlaceCategory;
import com.unear.pos.common.dto.enums.PlaceType;
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
    private PlaceType placeType;
    private EventParticipationStatus eventStatus;
    private PlaceCategory placeCategory;
    private String benefitCategory;
    private String tel;
    private Integer startTime;
    private Integer endTime;

    public static PosSessionInfo from(Owner owner, Place place) {
        return new PosSessionInfo(
                owner.getOwnerId(), owner.getOwnerName(), owner.getPosId(),
                place.getPlaceId(), place.getFranchiseId(), place.getPlaceName(),
                place.getPlaceDesc(), place.getAddress(), PlaceType.valueOf(place.getMarkerCode()),
                EventParticipationStatus.valueOf(place.getEventTypeCode()),
                PlaceCategory.valueOf(place.getCategoryCode()),
                place.getBenefitCategory(), place.getTel(), place.getStartTime(), place.getEndTime()
        );
    }
}
