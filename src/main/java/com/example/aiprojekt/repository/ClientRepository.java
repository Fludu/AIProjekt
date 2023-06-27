package com.example.aiprojekt.repository;

import com.example.aiprojekt.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    boolean existsByName(String name);

    Optional<Client> findByEmail(String email);
}