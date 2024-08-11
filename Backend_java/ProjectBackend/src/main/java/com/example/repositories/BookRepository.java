package com.example.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity,Long>{
@Query("Select b from BookEntity b order by b.updatedAt desc")
List<BookEntity> recentlyAddedBooks(Pageable page);
}
