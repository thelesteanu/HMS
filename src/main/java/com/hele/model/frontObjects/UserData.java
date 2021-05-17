package com.hele.model.frontObjects;

import com.hele.model.enums.RoleEnum;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Date;

/**
 * Created by thelesteanu on 26.04.2017.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserData {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String address;
    private Date birthDate;
    private String gender;
    private String bio;
    private OffsetDateTime registrationDate;
    private RoleEnum role;
    private Long hotel;
}
