package com.hele.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "room")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "room_number", nullable = false, length = 10)
    private String roomNumber;

    @Column(name = "size")
    private Long size;

    @Column(name = "smoking")
    private Boolean smoking;

    @Column(name = "pet_friendly")
    private Boolean petFriendly;

    @Column(name = "reserved")
    private Boolean reserved;

    @Column(name = "price")
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotelId;
}
