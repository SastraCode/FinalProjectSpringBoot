package com.banksultra.finalProject.service.serviceImpl;

import com.banksultra.finalProject.model.TripSchedule;
import com.banksultra.finalProject.repository.TripScheduleRepository;
import com.banksultra.finalProject.service.TripScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripScheduleServiceImpl implements TripScheduleService {
    @Autowired
    TripScheduleRepository tripScheduleRepository;

    public TripSchedule addTripSchedule(TripSchedule tripSchedule){
        return tripScheduleRepository.save(tripSchedule);
    }

    public List<TripSchedule> getTripSchedule(){
        return tripScheduleRepository.findAll();
    }

    public List<TripSchedule> tripschedules(String tgl){
        return tripScheduleRepository.tripschedules(tgl);
    }
}
