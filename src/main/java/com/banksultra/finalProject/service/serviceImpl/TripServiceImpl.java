package com.banksultra.finalProject.service.serviceImpl;

import com.banksultra.finalProject.model.Trip;
import com.banksultra.finalProject.repository.TripRepository;
import com.banksultra.finalProject.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {
    @Autowired
    TripRepository tripRepository;

    public Trip addTrip(Trip trip){
        return tripRepository.save(trip);
    }

    public List<Trip> getTrip(){
        return tripRepository.findAll();
    }

    public Trip tripsbystop(Long star, Long stop){
//        return tripRepository.;
        return tripRepository.tripsbystop(star, stop);
    }
}
