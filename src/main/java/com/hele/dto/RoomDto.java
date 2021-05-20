package com.hele.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
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
