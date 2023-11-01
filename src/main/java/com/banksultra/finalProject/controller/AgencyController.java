package com.banksultra.finalProject.controller;

import com.banksultra.finalProject.model.Agency;
import com.banksultra.finalProject.model.CustomeResponse;
import com.banksultra.finalProject.model.Ticket;
import com.banksultra.finalProject.service.AgencyService;
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
public class AgencyController {
    @Autowired
    AgencyService agencyService;

    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @GetMapping("/getAgency")
    public ResponseEntity<CustomeResponse<List<Agency>>> getAgency(){
        CustomeResponse<List<Agency>> response = new CustomeResponse<>();
        try {
            List<Agency> data = agencyService.getAgency();

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

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/addAgency", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomeResponse<Agency>> saveAgency(@RequestBody Agency agency){
        CustomeResponse<Agency> response = new CustomeResponse<>();
        try {
            Agency data = agencyService.saveAgency(agency);

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

}
