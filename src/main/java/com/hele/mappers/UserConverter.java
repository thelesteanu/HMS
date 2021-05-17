package com.hele.mappers;

import com.hele.dto.UserDto;
import com.hele.entity.User;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class UserConverter {

    private static Validator validator;

    public UserConverter() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public static UserDto toUserDto(final User user) {

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .address(user.getAddress())
                .birthDate(user.getBirthDate())
                .gender(user.getGender())
                .bio(user.getBio())
                .registrationDate(user.getRegistrationDate())
                .role(user.getRole())
                .hotelId(user.getHotelId() == null ? null : user.getHotelId().getId())
                .build();


    }

    public static User toUser(final UserDto userDto) {

        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .address(userDto.getAddress())
                .birthDate(userDto.getBirthDate())
                .gender(userDto.getGender())
                .bio(userDto.getBio())
                .registrationDate(userDto.getRegistrationDate())
                .role(userDto.getRole())
                .build();
    }
}
