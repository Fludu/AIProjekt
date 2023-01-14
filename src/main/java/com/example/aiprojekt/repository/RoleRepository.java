package com.example.aiprojekt.repository;

import com.example.aiprojekt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    public Role findByName(String name);
}
