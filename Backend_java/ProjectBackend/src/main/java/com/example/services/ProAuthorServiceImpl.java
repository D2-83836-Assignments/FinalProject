package com.example.services;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.ProAuthorRegisterDTO;
import com.example.entities.ProAuthorEntity;
import com.example.repositories.ProAuthorRepositiory;

@Service
@Transactional
public class ProAuthorServiceImpl implements ProAuthorService {
	@Autowired
	ModelMapper mapper;
	@Autowired
	ProAuthorRepositiory proAuthorRepo;
	@Override
	public String registerAuthor(ProAuthorRegisterDTO user) {
		// TODO Auto-generated method stub
		ProAuthorEntity author = mapper.map(user, ProAuthorEntity.class);
		try {
			author.setPhoto(user.getPhoto().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		proAuthorRepo.save(author);
		return "Author Sucessfully added";
	}

}
