package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.ProAuthorRegisterDTO;
import com.example.response.ApiResponseSuccess;
import com.example.services.ProAuthorService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/proAuthor")
@CrossOrigin
public class ProAuthorController {
	@Autowired
	private  ProAuthorService proAuthorservices;
	@PostMapping(value="/register",consumes = "multipart/form-data")
	public ResponseEntity<?> registerUser(@ModelAttribute @Valid ProAuthorRegisterDTO user){
	ApiResponseSuccess<String> response = new ApiResponseSuccess<String>();
	response.setData(proAuthorservices.registerAuthor(user));
	return ResponseEntity.ok(response);
	
}
}