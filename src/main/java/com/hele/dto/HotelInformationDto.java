package com.hele.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class HotelInformationDto {

    private Long hotelId;
    private String hotelName;
    private String hotelLocation;
}
