package com.hele.service;

import com.hele.dto.UserDto;
import com.hele.utils.Pagination;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    UserDto getUserById(final Long id);

    UserDto registerUser(final UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto updateUser(final UserDto userDto);

    Page<UserDto> getPageableUser(final Pagination pagination);

    UserDto updateUsernameAndRole(final UserDto userDto);
}
