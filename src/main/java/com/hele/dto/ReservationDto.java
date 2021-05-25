package com.hele.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ReservationDto {

    private Long id;
    private Date startDate;
    private Date endDate;
    private RoomInformationDto roomInformationDto;
    private Long userId;
}
