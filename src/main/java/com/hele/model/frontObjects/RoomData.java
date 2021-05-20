package com.hele.model.frontObjects;

import lombok.*;

/**
 * Created by thelesteanu on 08.05.2021.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomData {
    private Long id;
    private String number;
    private Long size;
    private Boolean smoking;
    private Boolean petFriendly;
    private Boolean reserved;
    private Long price;
    private Long hotelId;
}

