package com.example.hucodeuz.service;

import com.example.hucodeuz.entity.User;
import com.example.hucodeuz.exception.ResourceNotFoundException;
import com.example.hucodeuz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author "Husniddin Ulachov"
 * @created 12:12 AM on 7/13/2022
 * @project Edu-Center
 */
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        return userRepository.findByPhone(phone).orElseThrow(() -> new ResourceNotFoundException("user ", "  phone  ", phone));
    }
}
