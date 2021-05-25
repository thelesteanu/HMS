package com.hele.service;

import com.hele.dto.RoomDto;
import com.hele.utils.Pagination;
import com.hele.dto.RoomFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoomService {

    RoomDto getRoomById(final Long id);

    List<RoomDto> getRoomsByIds(final List<Long> roomIds);

    List<RoomDto> getAllRooms();

    Page<RoomDto> getPageableRoom(final Pagination pagination, final RoomFilter roomFilter);

    RoomDto saveRoom(final RoomDto roomDto);
}
