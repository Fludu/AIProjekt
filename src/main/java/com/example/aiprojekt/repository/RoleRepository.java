package com.example.aiprojekt.repository;

import com.example.aiprojekt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, String> {

    boolean existsByName(String name);

    Optional<Role> findByName(String name);

    Set<Role> findAllByNameIsIn(Set<String> roleNames);
}
