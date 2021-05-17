package com.hele.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoomInformationDto {
    private Long roomId;
    private String roomNumber;
    private Long size;
    private Boolean smoking;
    private Boolean petFriendly;
    private Boolean reserved;
    private Long price;
    private HotelInformationDto hotelInformationDto;
}
