package com.hele.model.mappers;

import com.hele.dto.ReservationDto;
import com.hele.model.frontObjects.BookingData;
import org.springframework.stereotype.Component;

@Component
public class FrontBookingMapper {

    public static BookingData toBooking(final ReservationDto reservationDto) {
        return BookingData.builder()
                .id(reservationDto.getId())
                .hotelId(reservationDto.getRoomInformationDto().getHotelInformationDto().getHotelId())
                .roomId(reservationDto.getRoomInformationDto().getRoomId())
                .startDate(reservationDto.getStartDate())
                .endDate(reservationDto.getEndDate())
                .userId(reservationDto.getUserId())
                .hotelName(reservationDto
                        .getRoomInformationDto()
                        .getHotelInformationDto()
                        .getHotelName())
                .hotelLocation(reservationDto
                        .getRoomInformationDto()
                        .getHotelInformationDto()
                        .getHotelLocation())
                .roomNumber(reservationDto.getRoomInformationDto().getRoomNumber())
                .build();
    }


}
