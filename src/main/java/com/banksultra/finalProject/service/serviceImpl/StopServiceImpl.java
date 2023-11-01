package com.banksultra.finalProject.service.serviceImpl;

import com.banksultra.finalProject.model.Stop;
import com.banksultra.finalProject.repository.StopRepository;
import com.banksultra.finalProject.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StopServiceImpl implements StopService {
    @Autowired
    StopRepository stopRepository;

    public Stop addStop(Stop stop){
        return stopRepository.save(stop);
    }

    public List<Stop> getAllStops(){
        return stopRepository.findAll();
    }

}
