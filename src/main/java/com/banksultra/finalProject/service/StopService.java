package com.banksultra.finalProject.service;

import com.banksultra.finalProject.model.Stop;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StopService {
    Stop addStop(Stop stop);

    List<Stop> getAllStops();
}
