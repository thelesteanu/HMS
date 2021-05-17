package com.hele.entity;

import com.hele.utils.Gender;
import com.hele.utils.Role;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity(name = "user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "username", unique = true, length = 50, nullable = false)
    private String username;

    @NotBlank
    @Column(name = "password", length = 500, nullable = false)
    private String password;

    @NotBlank
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @NotBlank
    @Email
    @Column(name = "email", length = 50, unique = true, nullable = false)
    private String email;

    @Column(name = "address")
    private String address;

    @NotNull
    @Past
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @NotNull
    @Column(name = "gender", columnDefinition = "ENUM('MALE','FEMALE')", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "bio", length = 500)
    private String bio;

    @NotNull
    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private OffsetDateTime registrationDate;

    @NotNull
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotelId;
}
