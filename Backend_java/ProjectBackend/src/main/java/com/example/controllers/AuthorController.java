package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.AuthorRegisterDTO;
import com.example.response.ApiResponseSuccess;
import com.example.services.AuthorService;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/author")
@CrossOrigin
public class AuthorController {
	@Autowired
	private  AuthorService services;
	@PostMapping(value="/register",consumes = "multipart/form-data")
	public ResponseEntity<?> registerUser(@ModelAttribute @Valid AuthorRegisterDTO user){
	ApiResponseSuccess<String> response = new ApiResponseSuccess<String>();
	response.setData(services.registerAuthor(user));
	return ResponseEntity.ok(response);
	
}
}