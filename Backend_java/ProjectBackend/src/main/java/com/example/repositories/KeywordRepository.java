package com.example.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entities.KeywordsEntity;

public interface KeywordRepository extends JpaRepository<KeywordsEntity, Long>{

}
