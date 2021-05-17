package com.hele.model.frontObjects;

import lombok.*;

/**
 * Created by thelesteanu on 27.04.2017.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelData {
    private Long id;
    private String name;
    private String location;
    private String description;
    private Long employeeNo;
    private Boolean availability;
    private Long earnings;
}
