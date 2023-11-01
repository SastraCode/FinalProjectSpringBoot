package com.banksultra.finalProject.service;

import com.banksultra.finalProject.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {
    Ticket addTicket(Ticket ticket);

    List<Ticket> getTicket();

    List<Ticket> getTicketById(Long id_ticket);

    List<Ticket> bookticket(String token, Long id_ticket);

}
