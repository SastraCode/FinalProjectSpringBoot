package com.banksultra.finalProject.repository;

import com.banksultra.finalProject.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, String> {
    String sql1 = "SELECT * FROM FINAL.AGENCY WHERE ID =:id";

    @Query(value = sql1, nativeQuery = true)
    public Agency getAgencyById(Long id);
}
