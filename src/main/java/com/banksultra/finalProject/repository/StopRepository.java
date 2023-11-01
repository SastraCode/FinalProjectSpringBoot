package com.banksultra.finalProject.repository;

import com.banksultra.finalProject.model.Bus;
import com.banksultra.finalProject.model.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepository extends JpaRepository<Stop, String> {
}
