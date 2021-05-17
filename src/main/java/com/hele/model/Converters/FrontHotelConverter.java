package com.hele.model.Converters;


import com.hele.dto.HotelDto;
import com.hele.model.frontObjects.HotelData;
import org.springframework.stereotype.Component;

@Component
public class FrontHotelConverter {

    public static HotelData toHotelData(final HotelDto hotelDto) {
        return HotelData.builder()
                .id(hotelDto.getId())
                .name(hotelDto.getHotelName())
                .location(hotelDto.getLocation())
                .description(hotelDto.getDescription())
                .employeeNo(hotelDto.getEmployeeNumber())
                .availability(hotelDto.getAvailability())
                .earnings(hotelDto.getEarnings())
                .build();
    }

    public static HotelDto toHotelDto(final HotelData hotelData) {
        return HotelDto.builder()
                .id(hotelData.getId())
                .hotelName(hotelData.getName())
                .location(hotelData.getLocation())
                .description(hotelData.getDescription())
                .employeeNumber(hotelData.getEmployeeNo())
                .availability(hotelData.getAvailability())
                .earnings(hotelData.getEarnings())
                .build();
    }
}
