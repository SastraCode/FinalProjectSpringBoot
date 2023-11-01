package com.banksultra.finalProject.controller;

import com.banksultra.finalProject.model.CustomeResponse;
import com.banksultra.finalProject.model.User;
import com.banksultra.finalProject.security.beans.LoginRequest;
import com.banksultra.finalProject.security.jwt.JwtResponse;
import com.banksultra.finalProject.security.jwt.JwtTokenProvider;
import com.banksultra.finalProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.ConnectException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userService.getUserByEmail(loginRequest.getEmail());

        String jwt = tokenProvider.generateToken(authentication, user);
        return ResponseEntity.ok(new JwtResponse(jwt));

    }

    @PostMapping(value = "v1/user/signUp", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomeResponse<User>> signUp(@RequestBody User user){
        CustomeResponse<User> response = new CustomeResponse<>();
        try {
            User data = userService.signUpUser(user);

            if (data == null){
                response.setCode(204);
                response.setMessage("Gagal Melakukan Pendaftaran");
                response.setData(null);
            }else {
                User userRole = userService.getUserByEmail(user.getEmail());

                if (userRole != null){
                    Integer addRole = userService.addUserRole(userRole.getId(), Long.parseLong(userRole.getRoles()));

                    if (addRole > 0){
                        response.setCode(200);
                        response.setMessage("Berhasil Melakukan Pendaftaran");
                        response.setData(data);
                    }else {
                        response.setCode(204);
                        response.setMessage("Gagal Insert Data User Role : "+addRole);
                        response.setData(null);
                    }
                }else {
                    response.setCode(204);
                    response.setMessage("Gagal Mendapatkan Data User By Email : "+userRole);
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
                response.setMessage("Gagal Melakukan Pendaftaran : " + e.getMessage());
            }
            response.setData(null);

            return ResponseEntity.status(response.getCode()).body(response);
        }
    }


}
