package com.hele.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class HotelInformationDto {

    private Long hotelId;
    private String hotelName;
    private String hotelLocation;
}
