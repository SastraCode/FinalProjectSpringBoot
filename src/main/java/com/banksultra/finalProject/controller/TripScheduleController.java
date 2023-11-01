package com.banksultra.finalProject.controller;

import com.banksultra.finalProject.model.CustomeResponse;
import com.banksultra.finalProject.model.Trip;
import com.banksultra.finalProject.model.TripSchedule;
import com.banksultra.finalProject.service.TripScheduleService;
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
public class TripScheduleController {
    @Autowired
    TripScheduleService tripScheduleService;

    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @PostMapping(value = "/addTripSchedule", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomeResponse<TripSchedule>> addTripSchedule(@RequestBody TripSchedule tripSchedule){
        CustomeResponse<TripSchedule> response = new CustomeResponse<>();
        try {
            TripSchedule data = tripScheduleService.addTripSchedule(tripSchedule);

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

    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @GetMapping("/getTripSchedule")
    public ResponseEntity<CustomeResponse<List<TripSchedule>>> getTripSchedule(){
        CustomeResponse<List<TripSchedule>> response = new CustomeResponse<>();
        try {
            List<TripSchedule> data = tripScheduleService.getTripSchedule();

            if (data.isEmpty()){
                response.setCode(204);
                response.setMessage("Data Tidak Ditemukan");
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

    //tripschedules
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/v1/reservation/tripschedules", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomeResponse<List<TripSchedule>>> tripschedules(@PathParam("tgl1") String tgl){
        CustomeResponse<List<TripSchedule>> response = new CustomeResponse<>();
        try {
            List<TripSchedule> data = tripScheduleService.tripschedules(tgl);

            if (data.isEmpty()){
                response.setCode(204);
                response.setMessage("Data Tidak Ditemukan");
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
}
