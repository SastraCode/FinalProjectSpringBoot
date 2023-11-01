package com.banksultra.finalProject.controller;

import com.banksultra.finalProject.model.CustomeResponse;
import com.banksultra.finalProject.model.Trip;
import com.banksultra.finalProject.service.TripService;
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
public class TripController {
    @Autowired
    TripService tripService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/addTrip", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomeResponse<Trip>> addTrip(@RequestBody Trip trip){
        CustomeResponse<Trip> response = new CustomeResponse<>();
        try {
            Trip data = tripService.addTrip(trip);

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
                response.setMessage("Data Tidak Ditemukan : " + e.getMessage());
            }
            response.setData(null);

            return ResponseEntity.status(response.getCode()).body(response);
        }
    }

    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @GetMapping("/getTrip")
    public ResponseEntity<CustomeResponse<List<Trip>>> getTrip(){
        CustomeResponse<List<Trip>> response = new CustomeResponse<>();
        try {
            List<Trip> data = tripService.getTrip();

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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/v1/reservation/tripsbystop", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomeResponse<Trip>> tripsbystop(@PathParam("star") Long star, @PathParam("stop") Long stop){
        CustomeResponse<Trip> response = new CustomeResponse<>();
        try {
            Trip data = tripService.tripsbystop(star, stop);

            if (data == null){
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
