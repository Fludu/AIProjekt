package com.example.aiprojekt.repository;

import com.example.aiprojekt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByEmail(String email);
    boolean existsByLogin(String login);
    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndLogin(String email, String login);
}