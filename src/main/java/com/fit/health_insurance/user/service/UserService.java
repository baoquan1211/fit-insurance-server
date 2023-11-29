package com.fit.health_insurance.user.service;

import com.fit.health_insurance.exception.NotFoundException;
import com.fit.health_insurance.user.dto.UserDto;
import com.fit.health_insurance.user.model.User;
import com.fit.health_insurance.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repo;
    private final ModelMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String email) throws NotFoundException {
        return repo.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public UserDto findByEmail(String email){
        User user = (User) loadUserByUsername(email);
        return mapper.map(user, UserDto.class);
    }
}
