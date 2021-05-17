package com.hele.model.Utils;

import com.hele.dto.RoomDto;
import com.hele.model.frontObjects.BookingData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Utils {

    public static List<BookingData> populateBookings(final List<RoomDto> roomDtoList,
                                                     final List<BookingData> bookings) {
        return bookings.stream().peek(bookingData -> {
            for (RoomDto r : roomDtoList) {
                if (bookingData.getRoomId().equals(r.getId())) {
                    bookingData.setRoomNumber(r.getRoomNumber());
                    bookingData.setHotelId(r.getHotelInformationDto().getHotelId());
                    bookingData.setHotelName(r.getHotelInformationDto().getHotelName());
                    bookingData.setHotelLocation(r.getHotelInformationDto().getHotelLocation());
                }
            }
        }).collect(Collectors.toList());
    }
}
