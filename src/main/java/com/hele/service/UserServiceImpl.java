package com.hele.service;


import com.hele.dto.UserDto;
import com.hele.entity.User;
import com.hele.mappers.UserMapper;
import com.hele.repository.HotelRepository;
import com.hele.repository.UserRepository;
import com.hele.utils.Pagination;
import com.hele.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository,
                           final HotelRepository hotelRepository) {
        this.userRepository = userRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(final Long id) {
        User user = userRepository.findOne(id);
        return UserMapper.toUserDto(user);
    }

    @Override
    @Transactional
    public UserDto registerUser(final UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        user.setRegistrationDate(OffsetDateTime.now());
        user.setRole(Role.CLIENT);
        user.setPassword(new BCryptPasswordEncoder(11).encode(userDto.getPassword()));

        return UserMapper.toUserDto(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        return UserMapper.toUserDto(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> getPageableUser(final Pagination pagination) {
        PageRequest pageRequest = new PageRequest(pagination.getPageNumber(), pagination.getPageSize());

        return userRepository.findAll(pageRequest).map(UserMapper::toUserDto);
    }

    @Override
    @Transactional
    public void updateUsernameAndRole(final UserDto userDto) {
        userRepository.updateUsernameAndRole(userDto.getId(), userDto.getUsername(), userDto.getRole());
    }
}
