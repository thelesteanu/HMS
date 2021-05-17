package com.hele.model.frontObjects;

import lombok.*;

import java.util.Date;

/**
 * Created by thelesteanu on 09.05.2017.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingData {
    private Long id;
    private Long hotelId;
    private Long roomId;
    private Date startDate;
    private Date endDate;
    private Long userId;
    private String hotelName;
    private String hotelLocation;
    private String roomNumber;
}
