package com.example.services;

import java.io.IOException;
import java.util.List;

import com.example.dto.BookAddDTO;
import com.example.dto.BookGetDTO;
import com.example.dto.BookViewDTO;
import com.example.dto.KeywordAddDTO;
import com.example.dto.ReviewAddDTO;

public interface BookService {

public String addKeyword(KeywordAddDTO keyword);
public String addBook(BookAddDTO book);
public List<BookGetDTO> getBooks();
public List<KeywordAddDTO> getKeywords();
public List<BookGetDTO> getRecentlyAddedBooks(int pageNo);
public BookViewDTO getBookById(Long id);
public List<ReviewAddDTO> getReviewsByBook(Long bookId);
}
