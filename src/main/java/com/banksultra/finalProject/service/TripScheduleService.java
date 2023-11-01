package com.banksultra.finalProject.service;

import com.banksultra.finalProject.model.TripSchedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TripScheduleService {
    TripSchedule addTripSchedule(TripSchedule tripSchedule);

    List<TripSchedule> getTripSchedule();

    List<TripSchedule> tripschedules(String tgl);
}
