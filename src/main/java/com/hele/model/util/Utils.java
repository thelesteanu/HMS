package com.hele.model.util;

import com.hele.dto.HotelDto;
import com.hele.dto.RoomDto;
import com.hele.model.frontObjects.BookingData;
import com.hele.model.frontObjects.RoomData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
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
     * Method used to generate a room. TO BE DELETED
     *
     * @return
     */
    private static RoomData generateRoom() {
        RoomData room = new RoomData();
        room.setId(ThreadLocalRandom.current().nextLong(10000));
        room.setNumber("" + ThreadLocalRandom.current().nextInt(10000));
        room.setPetFriendly(ThreadLocalRandom.current().nextBoolean());
        room.setReserved(ThreadLocalRandom.current().nextBoolean());
        room.setSmoking(ThreadLocalRandom.current().nextBoolean());
        room.setPrice(ThreadLocalRandom.current().nextLong(10000));
        room.setSize(ThreadLocalRandom.current().nextLong(8));
        return room;
    }

    /**
     * Method used to generate a list of rooms. TO BE DELETED
     *
     * @return
     */
    public static List<RoomData> generateRooms() {
        final List<RoomData> rooms = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            rooms.add(generateRoom());
        }

        return rooms;
    }

    /**
     * Method used to generate a mock booking. TO BE DELETED
     *
     * @return
     */
    public static BookingData generateBooking() {
        final BookingData booking = new BookingData();
        booking.setId(ThreadLocalRandom.current().nextLong(10000));
        booking.setHotelId(ThreadLocalRandom.current().nextLong(10000));
        booking.setUserId(ThreadLocalRandom.current().nextLong(10000));
        booking.setRoomId(ThreadLocalRandom.current().nextLong(10000));
        booking.setStartDate(new Date());
        booking.setHotelName("Hilton");
        booking.setHotelLocation("Paris");
        booking.setRoomNumber("13");
        booking.setEndDate(new Date());
        return booking;
    }





}
