package com.banksultra.finalProject.controller;

import com.banksultra.finalProject.model.CustomeResponse;
import com.banksultra.finalProject.model.User;
import com.banksultra.finalProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.net.ConnectException;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;


    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @PostMapping(value = "updateProfile", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomeResponse<User>> editUser(@PathParam("id") Long id, @RequestBody User user){
        CustomeResponse<User> response = new CustomeResponse<>();
        try {
            User data = userService.editUser(id, user);

            if (data == null){
                response.setCode(204);
                response.setMessage("Gagal Update Profile");
                response.setData(null);
            }else {
                System.out.println("Long.parseLong(user.getRoles()) =============================== "+ Long.parseLong(user.getRoles()));
                System.out.println("user.getId() =============================== "+ user.getId());
                Integer uRole =  userService.updateUserRole(user.getId(), Long.parseLong(user.getRoles()));
                System.out.println("uRole =============================== "+ uRole);
                if (uRole > 0){
                    response.setCode(200);
                    response.setMessage("Berhasil Melakukan Update Profile");
                    response.setData(data);
                }else{
                    response.setCode(204);
                    response.setMessage("Gagal Update User Role"+ uRole);
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
                response.setMessage("Gagal Melakukan Update Profile : " + e.getMessage());
            }
            response.setData(null);

            return ResponseEntity.status(response.getCode()).body(response);
        }
    }
}
