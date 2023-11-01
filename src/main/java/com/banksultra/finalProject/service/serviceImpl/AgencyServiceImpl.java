package com.banksultra.finalProject.service.serviceImpl;

import com.banksultra.finalProject.model.Agency;
import com.banksultra.finalProject.repository.AgencyRepository;
import com.banksultra.finalProject.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {
    @Autowired
    AgencyRepository agencyRepository;

    @Override
    public List<Agency> getAgency(){
        return agencyRepository.findAll();
    }
    @Override
    public Agency saveAgency(Agency agency){
        return agencyRepository.save(agency);
    }

//    @Override
//    public Agency editAgency(Long id, Agency agency){
//        Agency data = agencyRepository.getAgencyById(id);
//
//        if (data != null){
//            agency.setId(data.getId());
//            agency.setCode(data.getCode());
//            agency.setName(data.getName());
//            agency.setDetails(data.getDetails());
////            agency.se(data.getOwner());
//
//            return agencyRepository.save(agency);
//        }else{
//            return agencyRepository.save(agency);
//        }
//    }


}
