package com.hele.utils;

import com.hele.dto.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class Utils {

    /**
     * Method used to create a hotel. TO BE DELETED
     *
     * @return
     */
    private static HotelDto createHotelMock() {
        final HotelDto hotel = new HotelDto();
        hotel.setId(ThreadLocalRandom.current().nextLong(10000));
        hotel.setName("Hilton");
        hotel.setLocation("Paris");
        hotel.setEarnings((long) 1235839);
        hotel.setDescription("Hilton Hotels & Resorts is Hilton's flagship brand and one of the largest hotel brands in" +
                " the world.The brand is targeted at both business and leisure travelers with locations in major city " +
                "centers, near airports, convention centers, and popular vacation destinations around the world.");
        hotel.setEmployeeNumber(ThreadLocalRandom.current().nextLong(500));
        hotel.setAvailability(ThreadLocalRandom.current().nextBoolean());
        return hotel;
    }

    /**
     * Method used to generate a list of hotels. TO BE DELETED
     *
     * @return
     */
    public static List<HotelDto> generateMockHotels() {
        final List<HotelDto> hotels = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            hotels.add(createHotelMock());
        }

        return hotels;
    }

    /**
     * Method used to generate a mock booking. TO BE DELETED
     *
     * @return
     */
    public static ReservationDto generateBooking() {
        final ReservationDto booking = new ReservationDto();

        //final BookingData booking = new BookingData();
        booking.setId(ThreadLocalRandom.current().nextLong(10000));
        booking.setUserId(ThreadLocalRandom.current().nextLong(10000));
        booking.setStartDate(new Date());
        booking.setEndDate(new Date());

        HotelInformationDto hotelInfo = new HotelInformationDto();
        hotelInfo.setHotelId(ThreadLocalRandom.current().nextLong(10000));
        hotelInfo.setHotelName("Hilton");
        hotelInfo.setHotelLocation("Paris");

        RoomInformationDto roomInfo = new RoomInformationDto();
        roomInfo.setRoomId(ThreadLocalRandom.current().nextLong(10000));
        roomInfo.setRoomNumber("13");
        roomInfo.setHotelInformationDto(hotelInfo);

        booking.setRoomInformationDto(roomInfo);
        return booking;
    }





}
