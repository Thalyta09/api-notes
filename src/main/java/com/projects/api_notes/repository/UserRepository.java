package com.projects.api_notes.repository;

import com.projects.api_notes.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Login, Long> {
    Optional<Login> findByUsername(String username);
}