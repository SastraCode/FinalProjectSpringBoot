package com.banksultra.finalProject.service;

import com.banksultra.finalProject.model.Trip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TripService {
    Trip addTrip(Trip trip);

    List<Trip> getTrip();

    Trip tripsbystop(Long star, Long stop);
}
