package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.OrderAddDTO;
import com.example.dto.PublisherRegisterDTO;
import com.example.dto.RecieptDTO;
import com.example.response.ApiResponseSuccess;
import com.example.services.OrderService;
import com.example.services.PublisherService;


import jakarta.validation.Valid;



@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
	@Autowired
	private OrderService orderService;
	@PostMapping(value="/make")
	public ResponseEntity<?> makeOrder(@RequestBody @Valid OrderAddDTO order){
	ApiResponseSuccess<RecieptDTO> response = new ApiResponseSuccess<>();
	response.setData(orderService.placeOrder(order));
	return ResponseEntity.ok(response);
	
}
}