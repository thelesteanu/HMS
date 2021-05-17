package com.hele.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ReservationDto {

    private Long id;
    private Date startDate;
    private Date endDate;
    private RoomInformationDto roomInformationDto;
    private Long userId;
}
