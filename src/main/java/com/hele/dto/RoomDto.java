package com.hele.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class RoomDto {

    private Long id;
    private String roomNumber;
    private Long size;
    private Boolean smoking;
    private Boolean petFriendly;
    private Boolean reserved;
    private Long price;
    private Long hotelId;
    private HotelInformationDto hotelInformationDto;
}
