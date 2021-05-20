package com.hele.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDto {
    private Long id;
    private String name;
    private String location;
    private String description;
    private Long employeeNumber;
    private Boolean availability;
    private Long earnings;
}
