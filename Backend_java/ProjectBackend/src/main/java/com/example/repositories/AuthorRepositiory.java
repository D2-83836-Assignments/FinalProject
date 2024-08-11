package com.example.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entities.SelfAuthorEntity;


public interface AuthorRepositiory extends JpaRepository<SelfAuthorEntity, Long>{
public Optional<SelfAuthorEntity> findByEmail(String email);
public boolean existsByEmail(String email);
}
