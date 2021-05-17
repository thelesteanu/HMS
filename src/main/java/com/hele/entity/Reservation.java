package com.hele.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "reservation")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Future
    @NotNull
    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room roomId;

    @Column(name = "user_id")
    private Long userId;
}
