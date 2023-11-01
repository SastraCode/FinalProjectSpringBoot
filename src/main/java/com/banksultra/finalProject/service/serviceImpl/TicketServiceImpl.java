package com.banksultra.finalProject.service.serviceImpl;

import com.banksultra.finalProject.model.Ticket;
import com.banksultra.finalProject.model.TripSchedule;
import com.banksultra.finalProject.repository.TicketRepository;
import com.banksultra.finalProject.repository.TripScheduleRepository;
import com.banksultra.finalProject.security.jwt.JwtTokenProvider;
import com.banksultra.finalProject.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TripScheduleRepository tripScheduleRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public Ticket addTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTicket(){
        return ticketRepository.findAll();
    }

    public List<Ticket> getTicketById(Long id_ticket){
        return ticketRepository.bookticket(id_ticket);
    }

    public List<Ticket> bookticket(String token, Long id_ticket){
        Ticket ticket = ticketRepository.findById(id_ticket).orElse(null);

        String getId= jwtTokenProvider.getIdToken(token);

        Long id_user = Long.parseLong(getId);

        ticketRepository.updateEntity(id_user, id_ticket);

        TripSchedule tripSchedule  = tripScheduleRepository.findById(ticket.getTripSchedule().getId()).orElse(null);

        String data1 = tripSchedule.getTicketsSold();
        if (data1 == null){
            data1 = "";
        }
        String data2 = String.valueOf(id_ticket);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(data1);
        stringBuilder.append(data2);

        String gabungan = stringBuilder.toString();

        String dataWithCommas = gabungan.replace("|", " ");

        String dataTicketSold = String.join("|", dataWithCommas.split(" "));

        String ticketSold = dataTicketSold+"|";

        ticketRepository.updateTripSchedule(ticketSold,ticket.getTripSchedule().getId());

        return ticketRepository.bookticket(id_ticket);
    }


}
