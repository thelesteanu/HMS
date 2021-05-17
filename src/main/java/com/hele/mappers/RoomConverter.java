package com.hele.mappers;

import com.hele.dto.HotelInformationDto;
import com.hele.dto.RoomDto;
import com.hele.entity.Hotel;
import com.hele.entity.Room;

public class RoomConverter {

    public static RoomDto toDto(final Room room) {

        return RoomDto.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .size(room.getSize())
                .smoking(room.getSmoking())
                .petFriendly(room.getPetFriendly())
                .reserved(room.getReserved())
                .price(room.getPrice())
                .hotelInformationDto(getHotelInformation(room.getHotelId()))
                .build();
    }

    public static HotelInformationDto getHotelInformation(final Hotel hotel) {
        return HotelInformationDto.builder()
                .hotelId(hotel.getId())
                .hotelLocation(hotel.getLocation())
                .hotelName(hotel.getHotelName())
                .build();
    }

    public static Room toEntity(final RoomDto roomDto) {

        return Room.builder()
                .id(roomDto.getId())
                .roomNumber(roomDto.getRoomNumber())
                .size(roomDto.getSize())
                .smoking(roomDto.getSmoking())
                .petFriendly(roomDto.getPetFriendly())
                .reserved(roomDto.getReserved())
                .price(roomDto.getPrice())
                .build();
    }
}
