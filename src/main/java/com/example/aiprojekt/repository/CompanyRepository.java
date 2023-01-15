package com.example.aiprojekt.repository;

import com.example.aiprojekt.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {


    void deleteById(String id);

    boolean existsByName(String name);
}
