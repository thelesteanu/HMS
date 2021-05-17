package com.hele.model.Converters;

import com.hele.dto.UserDto;
import com.hele.utils.Gender;
import com.hele.utils.Role;
import com.hele.model.enums.RoleEnum;
import com.hele.model.frontObjects.UserData;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;


@Component
public class FrontUserConverter {

    public static UserDto toUserDto(final UserData userData) {

        return UserDto.builder()
                .id(userData.getId())
                .username((userData.getUsername()))
                .password(userData.getPassword())
                .firstName(userData.getFirstName())
                .lastName(userData.getLastName())
                .email(userData.getEmail())
                .address(userData.getAddress())
                .birthDate(userData.getBirthDate())
                .gender(userData.getGender() == null ? null : Gender.valueOf(userData.getGender()))
                .bio(userData.getBio())
                .registrationDate(userData.getRegistrationDate() == null ? OffsetDateTime.now() : userData.getRegistrationDate())
                .role(userData.getRole() == null ? Role.CLIENT : Role.valueOf(userData.getRole().name()))
                .hotelId(userData.getHotel())
                .build();
    }

    public static UserData toUserData(final UserDto userDto) {

        return UserData.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .address(userDto.getAddress())
                .birthDate(userDto.getBirthDate())
                .gender(userDto.getGender().toString())
                .bio(userDto.getBio())
                .registrationDate(userDto.getRegistrationDate())
                .role(RoleEnum.valueOf(userDto.getRole().toString()))
                .hotel(userDto.getHotelId())
                .build();
    }
}
