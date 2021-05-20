package com.hele.service;

import com.hele.entity.User;
import com.hele.repository.UserRepository;
import com.hele.mappers.HotelConverter;
import com.hele.dto.HotelDto;
import com.hele.entity.Hotel;
import com.hele.repository.HotelRepository;
import com.hele.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;

    @Autowired
    public HotelServiceImpl(final HotelRepository hotelRepository,
                            final UserRepository userRepository) {
        this.hotelRepository = hotelRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public HotelDto getHotelById(final Long id) {
        Hotel hotel = hotelRepository.findOne(id);
        return HotelConverter.toDto(hotel);
    }

    @Override
    public List<HotelDto> getHotelsByIds(final List<Long> hotelIds) {
        List<Hotel> hotels = hotelRepository.getAllByIdIs(hotelIds);
        return hotels.stream()
                .map(HotelConverter::toDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HotelDto> getPageableHotel(final Pagination pagination) {
        PageRequest pageRequest = new PageRequest(pagination.getPageNumber(), pagination.getPageSize());

        return hotelRepository.findAll(pageRequest)
                .map(HotelConverter::toDto);
    }

    @Override
    @Transactional
    public void updateHotel(HotelDto hotelDto) {
        hotelRepository.updateHotel(hotelDto.getId(), hotelDto.getName(),
                hotelDto.getDescription(), hotelDto.getEmployeeNumber(),
                hotelDto.getEarnings());
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelDto> getHotelsByOwnerId(final Long ownerId) {

        User owner = userRepository.findOne(ownerId);

        List<Hotel> hotels = hotelRepository.getAllByOwnerId(owner);
        return hotels.stream()
                .map(HotelConverter::toDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelDto> getAllHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(HotelConverter::toDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
