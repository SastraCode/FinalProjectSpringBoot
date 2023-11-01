package com.banksultra.finalProject.controller;

import com.banksultra.finalProject.model.CustomeResponse;
import com.banksultra.finalProject.model.Ticket;
import com.banksultra.finalProject.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.ConnectException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/addTicket", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomeResponse<Ticket>> addTicket(@RequestBody Ticket ticket){
        CustomeResponse<Ticket> response = new CustomeResponse<>();
        try {
            Ticket data = ticketService.addTicket(ticket);

            if (data == null){
                response.setCode(204);
                response.setMessage("Gagal Menambah Data");
                response.setData(null);
            }else {
                response.setCode(200);
                response.setMessage("Berhasil Menambah Data");
                response.setData(data);
            }

            return ResponseEntity.ok(response);
        }catch (Exception e){
            if (e instanceof ConnectException) {
                response.setCode(503);
                response.setMessage("Service Unavailable: " + e.getMessage());
            } else {
                response.setCode(500);
                response.setMessage("Gagal Menambah Data : " + e.getMessage());
            }
            response.setData(null);

            return ResponseEntity.status(response.getCode()).body(response);
        }
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
    @GetMapping("/getTicket")
    public ResponseEntity<CustomeResponse<List<Ticket>>> getTicket(){
        CustomeResponse<List<Ticket>> response = new CustomeResponse<>();
        try {
            List<Ticket> data = ticketService.getTicket();

            if (data.isEmpty()){
                response.setCode(204);
                response.setMessage("Data Tiket Tidak Ditemukan");
                response.setData(null);
            }else {
                response.setCode(200);
                response.setMessage("Berhasil Mendapatkan Data");
                response.setData(data);
            }

            return ResponseEntity.ok(response);
        }catch (Exception e){
            if (e instanceof ConnectException) {
                response.setCode(503);
                response.setMessage("Service Unavailable: " + e.getMessage());
            } else {
                response.setCode(500);
                response.setMessage("Data Tidak Ditemukan : " + e.getMessage());
            }
            response.setData(null);

            return ResponseEntity.status(response.getCode()).body(response);
        }
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
    @GetMapping(value = "/v1/reservation/bookticket", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomeResponse<List<Ticket>>> bookticket(@PathParam("id_ticket") Long id_ticket, @RequestHeader("Authorization") String authorizationHeader){
        CustomeResponse<List<Ticket>> response = new CustomeResponse<>();

        try {
            List<Ticket> dataTicket = ticketService.getTicketById(id_ticket);

            if (dataTicket.isEmpty()){
                response.setCode(204);
                response.setMessage("Data Tidak Ditemukan, Id Tiket Belum Terdaftar");
                response.setData(null);
            }else{
                if (dataTicket.get(0).getPassenger() == null){
                    String token = authorizationHeader.substring(7);

                    List<Ticket> data = ticketService.bookticket(token, id_ticket);

                    if (data.isEmpty()){
                        response.setCode(204);
                        response.setMessage("Gagal Booking Ticket");
                        response.setData(null);
                    }else {
                        response.setCode(200);
                        response.setMessage("Berhasil Booking Ticket");
                        response.setData(data);
                    }
                }else{
                    response.setCode(204);
                    response.setMessage("Tiket Yang Anda Pesan Sudah Terjual, Mohon Pilih TIket Yang Lain.");
                    response.setData(null);
                }
            }

            return ResponseEntity.ok(response);
        }catch (Exception e){
            if (e instanceof ConnectException) {
                response.setCode(503);
                response.setMessage("Service Unavailable: " + e.getMessage());
            } else {
                response.setCode(500);
                response.setMessage("Data Tidak Ditemukan : " + e.getMessage());
            }
            response.setData(null);

            return ResponseEntity.status(response.getCode()).body(response);
        }
    }
}
