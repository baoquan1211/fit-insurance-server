package com.fit.health_insurance.service;

import com.fit.health_insurance.dto.UserDto;
import com.fit.health_insurance.dto.UserUpdateRequest;
import com.fit.health_insurance.exception.InternalErrorException;
import com.fit.health_insurance.exception.NotFoundException;
import com.fit.health_insurance.model.User;
import com.fit.health_insurance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repo;
    private final ModelMapper mapper;
    private final CloudinaryService cloudinaryService;

    @Override
    public UserDetails loadUserByUsername(String email) throws NotFoundException {
        return repo.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public UserDto update(UserUpdateRequest request ) {
        var user = repo.findByEmail(request.getEmail()).orElseThrow(() -> new NotFoundException("User not found"));
        user.setPhone(request.getPhone());
        user.setIdentityCard(request.getIdentifyCard());
        try {
            if (request.getAvatarFile() != null) {
                String lastUrl = user.getAvatarUrl();
                user.setAvatarUrl(cloudinaryService.upload(request.getAvatarFile()));
                try {
                    cloudinaryService.delete(lastUrl);
                }
                catch (IOException e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new InternalErrorException("Could not upload avatar");
        }
        repo.save(user);
        return mapper.map(user, UserDto.class);
    }

    public UserDto findByEmail(String email) {
        User user = (User) loadUserByUsername(email);
        return mapper.map(user, UserDto.class);
    }
}
