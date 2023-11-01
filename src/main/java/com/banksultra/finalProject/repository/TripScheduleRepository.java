package com.banksultra.finalProject.repository;

import com.banksultra.finalProject.model.TripSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripScheduleRepository extends JpaRepository<TripSchedule, Long> {
    String sql1 ="SELECT * FROM FINAL.TRIP_SCHEDULE WHERE TRIP_DATE =:tgl";

    @Query(value = sql1, nativeQuery = true)
    List<TripSchedule> tripschedules(String tgl);
}
