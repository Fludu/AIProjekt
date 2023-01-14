package com.example.aiprojekt.service;

import com.example.aiprojekt.dto.UserCreateDto;
import com.example.aiprojekt.dto.UserReadDto;
import com.example.aiprojekt.models.Role;
import com.example.aiprojekt.models.User;
import com.example.aiprojekt.repository.RoleRepository;
import com.example.aiprojekt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    public UserReadDto createUser(UserCreateDto userCreateDto) {
        char[] encodedPassword = passwordEncoder.encode(userCreateDto.password()).toCharArray();
        Role userRole = roleRepository.findByName("ADMIN").orElseThrow();
        User registerUser = userRepository.save(new User(userCreateDto.email(), userCreateDto.login(), encodedPassword, userRole));
        return UserReadDto.of(registerUser);
    }
}
