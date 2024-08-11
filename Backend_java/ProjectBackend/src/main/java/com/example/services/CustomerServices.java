package com.example.services;

import java.util.List;

import com.example.dto.CustomerRegisterDTO;
import com.example.dto.ReviewAddDTO;

public interface CustomerServices {

public String registerCustomer(CustomerRegisterDTO user);
public List<Long> getBookIdByCustomer();
public String addReview(ReviewAddDTO reviewDto);
public String updateReview(ReviewAddDTO reviewDto);
List<Long> getBookRentedIdByCustomer();
}
