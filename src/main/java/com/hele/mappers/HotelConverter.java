package com.hele.mappers;

import com.hele.dto.HotelDto;
import com.hele.entity.Hotel;

public class HotelConverter {

    public static HotelDto toDto(final Hotel hotel) {

        return HotelDto.builder()
                .id(hotel.getId())
                .hotelName(hotel.getHotelName())
                .location(hotel.getLocation())
                .description(hotel.getDescription())
                .employeeNumber(hotel.getEmployeeNumber())
                .availability(hotel.getAvailability())
                .earnings(hotel.getEarnings())
                .build();
    }

    public static Hotel toEntity(final HotelDto hotelDto) {

        return Hotel.builder()
                .id(hotelDto.getId())
                .hotelName(hotelDto.getHotelName())
                .location(hotelDto.getLocation())
                .description(hotelDto.getDescription())
                .employeeNumber(hotelDto.getEmployeeNumber())
                .employeeNumber(hotelDto.getEmployeeNumber())
                .availability(hotelDto.getAvailability())
                .earnings(hotelDto.getEarnings())
                .build();
    }
}
