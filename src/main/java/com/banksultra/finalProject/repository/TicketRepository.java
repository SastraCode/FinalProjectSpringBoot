package com.banksultra.finalProject.repository;

import com.banksultra.finalProject.model.Ticket;
import com.banksultra.finalProject.model.TripSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    String slq = "UPDATE FINAL.TICKET SET PASSENGER =:id_user WHERE ID =:id_ticket";

    @Modifying
    @Transactional
    @Query(value = slq, nativeQuery = true)
    void updateEntity(Long id_user, Long id_ticket);

    String dataTicketSold = "SELECT * FROM FINAL.TRIP_SCHEDULE WHERE ID=:id_schedule";
    @Query(value = dataTicketSold, nativeQuery = true)
    TripSchedule ticketsSold(Long id_schedule);


    String sql2 = "UPDATE FINAL.TRIP_SCHEDULE SET TICKETS_SOLD=:dataTicketSold, AVAILABLE_SEATS = AVAILABLE_SEATS - 1 WHERE ID=:id_schedule";

    @Modifying
    @Transactional
    @Query(value = sql2, nativeQuery = true)
    void updateTripSchedule(String dataTicketSold, Long id_schedule);


    String sql3 = "SELECT * FROM FINAL.TICKET WHERE ID =:id_ticket";
    @Query(value = sql3, nativeQuery = true)
    List<Ticket> bookticket(Long id_ticket);
}
