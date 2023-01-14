package com.example.aiprojekt.service;

import com.example.aiprojekt.dto.RoleRequest;
import com.example.aiprojekt.models.Role;
import com.example.aiprojekt.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;


    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }


    public Role saveRole(RoleRequest roleRequest) {
        return roleRepository.save(RoleRequest.of(roleRequest));
    }
}
