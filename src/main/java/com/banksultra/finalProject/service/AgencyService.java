package com.banksultra.finalProject.service;

import com.banksultra.finalProject.model.Agency;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AgencyService {
    Agency saveAgency(Agency agencies);

//    Agency editAgency(Long id, Agency agency);

    List<Agency> getAgency();
}
