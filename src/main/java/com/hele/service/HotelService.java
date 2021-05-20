package com.hele.service;

import com.hele.dto.HotelDto;
import com.hele.utils.Pagination;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HotelService {

    HotelDto getHotelById(final Long id);

    List<HotelDto> getHotelsByIds(final List<Long> hotelIds);

    Page<HotelDto> getPageableHotel(final Pagination pagination);

    void createOrUpdate(final HotelDto hotelDto);

    List<HotelDto> getHotelsByOwnerId(final Long ownerId);

    List<HotelDto> getAllHotels();
}
