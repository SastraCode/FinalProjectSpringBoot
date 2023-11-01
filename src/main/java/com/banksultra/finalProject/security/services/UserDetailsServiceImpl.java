package com.banksultra.finalProject.security.services;

import com.banksultra.finalProject.mappers.UserMapper;
import com.banksultra.finalProject.model.User;
import com.banksultra.finalProject.repository.UserRepository;
import com.banksultra.finalProject.security.beans.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User Tdak Ditemukan"));
        UserPrincipal pr = UserMapper.userToPrincipal(user);
        pr.getAuthorities().stream().map(auth -> auth.getAuthority()).forEach(System.out::println);

        return UserMapper.userToPrincipal(user);
    }

}
