package com.banksultra.finalProject.repository;

import com.banksultra.finalProject.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, String> {

//    List<Trip> findBySourceStopIdAndDestStopId(Long star, Long stop);
    String sql1 = "SELECT * FROM FINAL.TRIP WHERE DEST_STOP=:star AND SOURCE_STOP=:stop";

    @Query(value = sql1, nativeQuery = true)
    Trip tripsbystop(Long star, Long stop);
}
