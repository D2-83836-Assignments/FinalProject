package com.example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.ProAuthorEntity;

public interface ProAuthorRepositiory extends JpaRepository<ProAuthorEntity, Long>{
public Optional<ProAuthorEntity> findByEmail(String email);
public boolean existsByEmail(String email);
}
