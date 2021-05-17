package com.hele.model.Converters;

import com.hele.dto.RoomDto;
import com.hele.model.frontObjects.RoomData;
import org.springframework.stereotype.Component;

@Component
public class FrontRoomConverter {

    public static RoomData toRoomData(final RoomDto roomDto) {
        return RoomData.builder()
                .id(roomDto.getId())
                .number(roomDto.getRoomNumber())
                .size(roomDto.getSize())
                .smoking(roomDto.getSmoking())
                .petFriendly(roomDto.getPetFriendly())
                .reserved(roomDto.getReserved())
                .price(roomDto.getPrice())
                .hotelId(roomDto.getHotelId())
                .build();
    }
}
