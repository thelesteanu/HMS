package com.hele.UnitTests;


import com.hele.dto.ReservationDto;
import com.hele.entity.Reservation;
import com.hele.repository.ReservationRepository;
import com.hele.service.ReservationService;
import com.hele.service.ReservationServiceImpl;
import com.hele.dto.HotelInformationDto;
import com.hele.dto.RoomInformationDto;
import com.hele.entity.Hotel;
import com.hele.entity.Room;
import com.hele.repository.RoomRepository;
import com.hele.utils.Pagination;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ReservationTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RoomRepository roomRepository;

    private ReservationService reservationService;

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void beforeEachTest() {
        initMocks(this);

        reservationService = new ReservationServiceImpl(reservationRepository, roomRepository);
    }

    @Test
    public void testBookReservation(){
        HotelInformationDto hotelInformationDto = HotelInformationDto.builder()
                .hotelId(1L)
                .build();

        RoomInformationDto roomInformationDto = RoomInformationDto.builder()
                .roomId(1L)
                .hotelInformationDto(hotelInformationDto)
                .build();

        ReservationDto reservationdto = ReservationDto.builder()
                .id(1L)
                .startDate(new Date(100, 7, 1))
                .endDate(new Date(100, 7, 10))
                .roomInformationDto(roomInformationDto)
                .userId(1L)
                .build();

        Hotel hotel = Hotel.builder()
                .id(1L)
                .build();

        Room room = Room.builder()
                .id(1L)
                .hotelId(hotel)
                .build();

        Reservation reservation = Reservation.builder()
                .id(1L)
                .startDate(new Date(100, 7, 1))
                .endDate(new Date(100, 7, 10))
                .roomId(room)
                .userId(1L)
                .build();

        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        when(roomRepository.findOne(any(Long.class))).thenReturn(room);

        ReservationDto reservationDto = reservationService.registerReservation(reservationdto);

        assertNotNull(reservationDto);
        assertEquals(reservationDto.getId(), reservationdto.getId());
        assertEquals(reservationDto.getUserId(), reservationdto.getId());
        assertEquals(reservationDto.getStartDate(), reservationdto.getStartDate());
        assertEquals(reservationDto.getEndDate(), reservationdto.getEndDate());
    }

    @Test
    public void testGetPageableReservations() {
        Hotel hotel = Hotel.builder()
                .id(1L)
                .build();

        Room room = Room.builder()
                .id(1L)
                .hotelId(hotel)
                .build();

        Reservation reservation = Reservation.builder()
                .id(1L)
                .startDate(new Date(100, 7, 1))
                .endDate(new Date(100, 7, 10))
                .roomId(room)
                .userId(1L)
                .build();

        PageRequest pageRequest = new PageRequest(0, 5);

        when(reservationRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(Arrays.asList(reservation)));

        Pagination pagination = new Pagination(0, 5);

        Page<ReservationDto> pageOfReservations = reservationService.getPageableReservation(pagination);

        assert (pageOfReservations.getTotalElements() == 1);
        assertNotNull(pageOfReservations.getContent().get(0));

        ReservationDto reservationDto = pageOfReservations.getContent().get(0);

        assertEquals(reservationDto.getId(), reservation.getId());
        assertEquals(reservationDto.getUserId(), reservation.getId());
        assertEquals(reservationDto.getStartDate(), reservation.getStartDate());
        assertEquals(reservationDto.getEndDate(), reservation.getEndDate());
    }

    @Test
    public void getReservationById() {
        Hotel hotel = Hotel.builder()
                .id(1L)
                .build();

        Room room = Room.builder()
                .id(1L)
                .hotelId(hotel)
                .build();

        Reservation reservation = Reservation.builder()
                .id(1L)
                .startDate(new Date(100, 7, 1))
                .endDate(new Date(100, 7, 10))
                .roomId(room)
                .userId(1L)
                .build();

        Long id = reservation.getId();

        when(reservationRepository.findOne(id)).thenReturn(reservation);

        ReservationDto reservationDto = reservationService.getReservationById(1L);

        assertNotNull(reservationDto);
        assertEquals(reservationDto.getId(), reservation.getId());
        assertEquals(reservationDto.getUserId(), reservation.getId());
        assertEquals(reservationDto.getStartDate(), reservation.getStartDate());
        assertEquals(reservationDto.getEndDate(), reservation.getEndDate());
    }

    @Test
    public void getReservationByUserId() {
        Hotel hotel = Hotel.builder()
                .id(1L)
                .build();

        Room room = Room.builder()
                .id(1L)
                .hotelId(hotel)
                .build();

        Reservation reservation = Reservation.builder()
                .id(1L)
                .startDate(new Date(100, 7, 1))
                .endDate(new Date(100, 7, 10))
                .roomId(room)
                .userId(1L)
                .build();

        Long id = reservation.getId();

        PageRequest pageRequest = new PageRequest(0, 5);

        when(reservationRepository.getReservationsByUserId(id, pageRequest)).thenReturn(new PageImpl<>(Arrays.asList(reservation)));

        Pagination pagination = new Pagination(0, 5);

        Page<ReservationDto> pageOfReservations = reservationService.getReservationsByUserId(pagination, 1L);

        assert (pageOfReservations.getTotalElements() == 1);
        assertNotNull(pageOfReservations.getContent().get(0));

        ReservationDto reservationDto = pageOfReservations.getContent().get(0);

        assertEquals(reservationDto.getId(), reservation.getId());
        assertEquals(reservationDto.getUserId(), reservation.getId());
        assertEquals(reservationDto.getStartDate(), reservation.getStartDate());
        assertEquals(reservationDto.getEndDate(), reservation.getEndDate());
    }

    @Test
    public void getReservationsByHotelId() {
        Hotel hotel = Hotel.builder()
                .id(1L)
                .build();

        Room room = Room.builder()
                .id(1L)
                .hotelId(hotel)
                .build();

        Reservation reservation = Reservation.builder()
                .id(1L)
                .startDate(new Date(100, 7, 1))
                .endDate(new Date(100, 7, 10))
                .roomId(room)
                .userId(1L)
                .build();

        List<Long> hotelIds = new ArrayList<>();

        hotelIds.add(1L);

        PageRequest pageRequest = new PageRequest(0, 5);

        when(reservationRepository.getReservationsByHotelIds(hotelIds, pageRequest)).thenReturn(new PageImpl<>(Arrays.asList(reservation)));

        Pagination pagination = new Pagination(0, 5);

        Page<ReservationDto> pageOfReservations = reservationService.getReservationsByHotelIds(pagination, hotelIds);

        assert (pageOfReservations.getTotalElements() == 1);
        assertNotNull(pageOfReservations.getContent().get(0));

        ReservationDto reservationDto = pageOfReservations.getContent().get(0);

        assertEquals(reservationDto.getId(), reservation.getId());
        assertEquals(reservationDto.getUserId(), reservation.getId());
        assertEquals(reservationDto.getStartDate(), reservation.getStartDate());
        assertEquals(reservationDto.getEndDate(), reservation.getEndDate());
    }
}
