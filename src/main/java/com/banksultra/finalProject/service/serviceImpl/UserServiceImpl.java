package com.banksultra.finalProject.service.serviceImpl;

import com.banksultra.finalProject.model.User;
import com.banksultra.finalProject.repository.UserRepository;
import com.banksultra.finalProject.service.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User signUpUser(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User editUser(Long id, User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User data = userRepository.getUserById(id);

        if (data != null){
            user.setId(user.getId());
            user.setEmail(user.getEmail());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setFirstName(user.getFirstName());
            user.setLastName(user.getLastName());
            user.setMobileNumber(user.getMobileNumber());
            user.setRole(user.getRole());

            return userRepository.save(user);
        }else{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
    }

    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    public Integer addUserRole(Long user_id, Long role_id){
        return userRepository.addUserRole(user_id, role_id);
    }

    public Integer updateUserRole(Long user_id, Long role_id){

        userRepository.updateUserRole(user_id, role_id);

        return userRepository.addUserRole(user_id, role_id);
    }
}
