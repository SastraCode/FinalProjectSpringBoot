package com.banksultra.finalProject.service.serviceImpl;

import com.banksultra.finalProject.model.Bus;
import com.banksultra.finalProject.repository.BusRepository;
import com.banksultra.finalProject.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImpl implements BusService {
    @Autowired
    BusRepository busRepository;

    @Override
    public Bus addBuses(Bus bus){
        return busRepository.save(bus);
    }

    @Override
    public List<Bus> getBus(){
        return busRepository.findAll();
    }

}
