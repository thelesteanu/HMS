package com.hele.service;

import com.hele.dto.ReservationDto;
import com.hele.utils.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {

    ReservationDto getReservationById(final Long id);

    Page<ReservationDto> getReservationsByUserId(final Pagination pagination, final Long id);

    Page<ReservationDto> getPageableReservation(final Pagination pagination);

    Page<ReservationDto> getReservationsByHotelIds(final Pagination pagination, final List<Long> ids);

    ReservationDto registerReservation(final ReservationDto reservationDto);
}
