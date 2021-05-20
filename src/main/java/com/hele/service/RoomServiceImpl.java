package com.hele.service;

import com.hele.mappers.RoomMapper;
import com.hele.dto.RoomDto;
import com.hele.entity.Room;
import com.hele.repository.HotelRepository;
import com.hele.repository.RoomRepository;
import com.hele.utils.Pagination;
import com.hele.model.filters.RoomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public RoomServiceImpl(final RoomRepository roomRepository,
                           final HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public RoomDto getRoomById(final Long id) {
        Room room = roomRepository.findOne(id);
        return RoomMapper.toDto(room);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomDto> getRoomsByIds(List<Long> roomIds) {
        List<Room> rooms = roomRepository.getAllByIds(roomIds);
        return rooms.stream()
                .map(RoomMapper::toDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomDto> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(RoomMapper::toDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RoomDto> getPageableRoom(final Pagination pagination, RoomFilter roomFilter) {
        PageRequest pageRequest = new PageRequest(pagination.getPageNumber(), pagination.getPageSize());

        RoomSpecification spec = new RoomSpecification(roomFilter);

        return roomRepository.findAll(spec, pageRequest).map(RoomMapper::toDto);
    }

    @Override
    public RoomDto saveRoom(RoomDto roomDto) {
        Room room = RoomMapper.toEntity(roomDto);

        room.setHotelId(hotelRepository.findOne(roomDto.getHotelId()));

        return RoomMapper.toDto(roomRepository.save(room));
    }
}
