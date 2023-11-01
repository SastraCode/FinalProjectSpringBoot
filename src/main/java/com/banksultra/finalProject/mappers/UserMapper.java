package com.banksultra.finalProject.mappers;

import com.banksultra.finalProject.model.User;
import com.banksultra.finalProject.security.beans.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserPrincipal userToPrincipal(User user) {

        List<SimpleGrantedAuthority> authorities = user.getRole()
                .stream()
                .map(role ->new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .collect(Collectors.toList());

        return new UserPrincipal()
                .setUsername(user.getEmail())
                .setPassword(user.getPassword())
                .setEnabled(true)
                .setAuthorities(authorities);

    }

}
