package com.hele.repository;

import com.hele.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Page<Reservation> getReservationsByUserId(final Long id, final Pageable pageable);

    @Query("select r from reservation r " +
            "inner join room ro on r.roomId = ro.id " +
            "inner join hotel h on ro.id = h.id " +
            "and h.id in ?1")
    Page<Reservation> getReservationsByHotelIds(final List<Long> ids, final Pageable pageable);
}
