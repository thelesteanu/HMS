package com.hele.dto;

import com.hele.utils.Gender;
import com.hele.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Date birthDate;
    private Gender gender;
    private String bio;
    private OffsetDateTime registrationDate;
    private Role role;
    private Long hotelId;
}
