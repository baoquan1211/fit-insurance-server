package com.fit.health_insurance.user.service;

import com.fit.health_insurance.exception.NotFoundException;
import com.fit.health_insurance.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws NotFoundException {
        return repo.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
    }
}
