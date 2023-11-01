package com.banksultra.finalProject.service;

import com.banksultra.finalProject.model.Bus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusService {
    Bus addBuses(Bus bus);

    List<Bus> getBus();
}
