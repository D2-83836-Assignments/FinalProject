package com.example.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.example.entities.Status;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class BookViewDTO {
	private String isbn;
	private String bookTitle;
	private String bookSubtitle;
	double basePrice;
	boolean isRentable;
	String longDesc;
	String shortDesc;
	Long publisherId;
	String coverImage;
	int pages;
	LocalDate datePublished;
	Long categoryId;
	List<KeywordAddDTO> keywords;
	List<String> authors;
}
