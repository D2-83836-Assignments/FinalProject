package com.example.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.PublisherRegisterDTO;
import com.example.entities.PublisherEntity;
import com.example.repositories.PublisherRepository;

@Service
@Transactional
public class PublisherServicesImpl implements PublisherService {
	@Autowired
	PublisherRepository publisherRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PasswordEncoder encoder;
	@Override
	public String registerPublisher(PublisherRegisterDTO user) {
		
		PublisherEntity publisher = mapper.map(user, PublisherEntity.class);
		publisher.setPassword(encoder.encode(user.getPassword()));
		publisherRepo.save(publisher);
		return "Publisher added";
	}

}
