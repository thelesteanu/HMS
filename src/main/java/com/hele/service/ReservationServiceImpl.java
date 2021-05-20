package com.hele.service;

import com.hele.mappers.ReservationMapper;
import com.hele.dto.ReservationDto;
import com.hele.entity.Reservation;
import com.hele.repository.ReservationRepository;
import com.hele.repository.RoomRepository;
import com.hele.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    private final RoomRepository roomRepository;

    @Autowired
    public ReservationServiceImpl(final ReservationRepository reservationRepository,
                                  final RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ReservationDto getReservationById(final Long id) {
        return ReservationMapper.toDto(reservationRepository.findOne(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReservationDto> getReservationsByUserId(final Pagination pagination, final Long id) {
        PageRequest pageRequest = new PageRequest(pagination.getPageNumber(), pagination.getPageSize());

        return reservationRepository.getReservationsByUserId(id, pageRequest)
                .map(ReservationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReservationDto> getPageableReservation(final Pagination pagination) {
        PageRequest pageRequest = new PageRequest(pagination.getPageNumber(), pagination.getPageSize());

        return reservationRepository.findAll(pageRequest)
                .map(ReservationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReservationDto> getReservationsByHotelIds(final Pagination pagination, final List<Long> ids) {
        PageRequest pageRequest = new PageRequest(pagination.getPageNumber(), pagination.getPageSize());

        return reservationRepository.getReservationsByHotelIds(ids, pageRequest)
                .map(ReservationMapper::toDto);
    }

    @Override
    @Transactional
    public ReservationDto registerReservation(final ReservationDto reservationDto) {
        Reservation reservation = ReservationMapper.toReservation(reservationDto);

        reservation.setRoomId(roomRepository.findOne(reservationDto.getRoomInformationDto().getRoomId()));

        return ReservationMapper.toDto(reservationRepository.save(reservation));
    }
}
