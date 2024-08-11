package com.example.services;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.AuthorRegisterDTO;
import com.example.entities.SelfAuthorEntity;
import com.example.repositories.AuthorRepositiory;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	private AuthorRepositiory repo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PasswordEncoder encoder;
	@Override
	public String registerAuthor(AuthorRegisterDTO user) {
		// TODO Auto-generated method stub
		SelfAuthorEntity author = mapper.map(user, SelfAuthorEntity.class);
		author.setPassword(encoder.encode(author.getPassword()));
		try {
			author.setPhoto(user.getPhoto().getBytes());
			author.setIdentification(user.getIdentification().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repo.save(author);
		return "Added successfully";
	}

}
