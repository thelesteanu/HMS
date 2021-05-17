package com.hele.mappers;

import com.hele.dto.ReservationDto;
import com.hele.entity.Reservation;
import com.hele.dto.RoomInformationDto;
import com.hele.entity.Room;

import static com.hele.mappers.RoomConverter.getHotelInformation;

public class ReservationConverter {

    public static ReservationDto toDto(final Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .roomInformationDto(getRoomInformation(reservation.getRoomId()))
                .userId(reservation.getUserId())
                .build();
    }

    private static RoomInformationDto getRoomInformation(Room room) {
        return RoomInformationDto.builder()
                .roomId(room.getId())
                .roomNumber(room.getRoomNumber())
                .size(room.getSize())
                .smoking(room.getSmoking())
                .petFriendly(room.getPetFriendly())
                .reserved(room.getReserved())
                .price(room.getPrice())
                .hotelInformationDto(getHotelInformation(room.getHotelId()))
                .build();
    }

    public static Reservation toReservation(final ReservationDto reservationDto) {

        return Reservation.builder()
                .id(reservationDto.getId())
                .startDate(reservationDto.getStartDate())
                .endDate(reservationDto.getEndDate())
                .userId(reservationDto.getUserId())
                .build();
    }
}
