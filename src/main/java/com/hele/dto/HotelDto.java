package com.hele.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class HotelDto {

    private Long id;
    private String hotelName;
    private String location;
    private String description;
    private Long employeeNumber;
    private Boolean availability;
    private Long earnings;
}
