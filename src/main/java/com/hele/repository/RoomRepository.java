package com.hele.repository;

import com.hele.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {

    @Query(value = "SELECT r FROM room r WHERE r.id IN ?1")
    List<Room> getAllByIds(final List<Long> ids);
}
