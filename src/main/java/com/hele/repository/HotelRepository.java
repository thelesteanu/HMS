package com.hele.repository;

import com.hele.entity.User;
import com.hele.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> getAllByIdIs(final List<Long> ids);

    @Modifying
    @Query("update hotel h set h.hotelName =?2, h.description = ?3, " +
            "h.employeeNumber =?4, h.earnings = ?5 where h.id = ?1")
    void updateHotel(final Long id, final String name,
                          final String description, final Long employeeNumber,
                          final Long earnings);

    List<Hotel> getAllByOwnerId(final User owner);
}
