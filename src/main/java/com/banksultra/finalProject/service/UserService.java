package com.banksultra.finalProject.service;

import com.banksultra.finalProject.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User signUpUser(User user);

    User editUser(Long id, User user);

    User getUserByEmail(String email);

    Integer addUserRole(Long user_id, Long role_id);

    Integer updateUserRole(Long user_id, Long role_id);
}
