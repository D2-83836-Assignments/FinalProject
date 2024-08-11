package com.example.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.CustomerRegisterDTO;
import com.example.dto.ReviewAddDTO;
import com.example.entities.CustomerEntity;
import com.example.entities.OrderItemEntity;
import com.example.entities.RentEntity;
import com.example.entities.ReviewEntity;
import com.example.repositories.BookRepository;
import com.example.repositories.CustomerRepository;
import com.example.repositories.OrderRepository;
import com.example.repositories.RentItemRepositiory;
import com.example.repositories.ReviewRepository;

@Service
@Transactional
public class CustomerServicesImpl implements CustomerServices{
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private UserServices userService;
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private ReviewRepository reviewRepo;
	@Autowired
	private RentItemRepositiory rentRepo;
	@Override
	public String registerCustomer(CustomerRegisterDTO user) {
		CustomerEntity user1 = mapper.map(user, CustomerEntity.class);
		user1.setPassword(encoder.encode(user1.getPassword()));
		customerRepo.save(user1);
		return "Customer added";
	}
	@Override
	public List<Long> getBookIdByCustomer() {
		String email = userService.getUserMail();
		List<OrderItemEntity> orderItems = orderRepo.bookIdsofBooksPurchasedByCustomer(email); 
		List<Long> bookIds = orderItems.stream().map(ele->ele.getBook().getId()).collect(Collectors.toList());
		return bookIds;
	}
	
	@Override
	public List<Long> getBookRentedIdByCustomer() {
		String email = userService.getUserMail();
		List<RentEntity> orderItems = rentRepo.bookIdsofBooksRentedByCustomer(email); 
		List<Long> bookIds = orderItems.stream().map(ele->ele.getBook().getId()).collect(Collectors.toList());
		return bookIds;
	}
	@Override
	public String addReview(ReviewAddDTO reviewDto) {
		CustomerEntity customer = customerRepo.findById(userService
				.getUserId(userService.getUserMail())).orElseThrow();
		if(getBookIdByCustomer().contains(reviewDto.getBookId())) {
		ReviewEntity review = mapper.map(reviewDto, ReviewEntity.class);
		review.setCustomer(customer);
		review.setBook(bookRepo.findById(reviewDto.getBookId()).orElseThrow());
		reviewRepo.save(review);
		return "Comment added successfully";
		}
		else {
			return "comment unsucessfull";
		}
	}
	@Override
	public String updateReview(ReviewAddDTO reviewDto) {
		ReviewEntity review = reviewRepo.findById(reviewDto.getId()).orElseThrow();
		review.setComment(reviewDto.getComment());
		review.setRating(reviewDto.getRating());
		return "comment updated";
	}

}
