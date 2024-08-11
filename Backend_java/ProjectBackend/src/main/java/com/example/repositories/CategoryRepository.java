package com.example.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.CategoryEntity;


public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

}
