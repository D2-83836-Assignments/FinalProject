package com.example.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.OrderAddDTO;
import com.example.dto.RecieptDTO;
import com.example.dto.RecieptItemsDTO;
import com.example.entities.BookEntity;
import com.example.entities.CustomerEntity;
import com.example.entities.OrderEntity;
import com.example.entities.OrderItemEntity;
import com.example.entities.OrderStatus;
import com.example.entities.RentEntity;
import com.example.exception.exceptions.UniqueConstraintViolationException;
import com.example.repositories.BookRepository;
import com.example.repositories.CustomerRepository;
import com.example.repositories.OrderRepository;
import com.example.repositories.RentItemRepositiory;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private UserServices userServices;
	@Override
	public RecieptDTO placeOrder(OrderAddDTO orderDto) {
		CustomerEntity customer  = customerRepo.findById(userServices.
				getUserId(userServices.getUserMail())).orElseThrow();
		
//		List<OrderItemEntity> orderItemsForIds = orderRepo.bookIdsofBooksPurchasedByCustomer(customer.getEmail()); 
//		List<RentEntity> rentItemsForIds = rentRepo.bookIdsofBooksRentedByCustomer(customer.getEmail());
//		List<Long> bookIdsFromCustomer = orderItemsForIds.stream().map(ele->ele.getBook().getId()).collect(Collectors.toList());
//		List<Long> bookIdsRentedFromCustomer = rentItemsForIds.stream().map(ele->ele.getBook().getId()).collect(Collectors.toList());
//		if(new HashSet<>(bookIdsFromCustomer).equals(new HashSet<>(orderDto.getItemIds()))||bookIdsFromCustomer.size()==0) {
//			throw new UniqueConstraintViolationException("Items already purchased");
//		}
//		if(new HashSet<>(bookIdsRentedFromCustomer).equals(new HashSet<>(orderDto.getRentItems()))||bookIdsRentedFromCustomer.size()==0){
//			throw new UniqueConstraintViolationException("Items already rented");
//		}
		OrderEntity order = new OrderEntity();
		String orderNo = new java.math.BigInteger(336,new Random()).toString().substring(0,18);
		order.setOrderNo(orderNo);
		order.setOrderDate(LocalDateTime.now());
		order.setCustomer(customer);
		List<BookEntity> items = bookRepo.findAllById(orderDto.getItemIds());
		items.forEach(item->{
			if((item.getCategory().getParent().getName().equals("Educational")
					&&((customer.getProfession().name().equals("TEACHER")||
							(customer.getProfession().name().equals("STUDENT")))))){
				order.addItems(item, item.getBasePrice(), 10.5);
			}
			else {
				order.addItems(item, item.getBasePrice(), 0);
			}
		});
		orderDto.getRentItems().forEach(item->{
			BookEntity book = bookRepo.findById(item.getId()).orElseThrow();
			order.addRentItems(book,book.getRentPerDay(), item.getRentLastDate());
		});
		order.setOrderStatus(OrderStatus.SUCCESS);
		orderRepo.save(order);
		RecieptDTO reciept = new RecieptDTO();
		reciept.setCustomerName(customer.getFirstName() + " " +customer.getLastName());
		reciept.setOrderDate(order.getOrderDate());
		double baseAmt = order.getOrderItems().stream().mapToDouble(ele->ele.getBasePrice()).sum();
		double disAmt = order.getOrderItems().stream().mapToDouble(ele->ele.getBasePrice()*ele.getDiscountPercent()/100).sum();
		double netAmt = baseAmt - disAmt;
		reciept.setBaseAmt(baseAmt);
		reciept.setDiscountAmt(disAmt);
		reciept.setNetAmt(netAmt);
		reciept.setOrderNo(order.getOrderNo());
		List<RecieptItemsDTO> recieptItems = order.getOrderItems().stream().map(ele->{
			RecieptItemsDTO rtd = new RecieptItemsDTO();
			BookEntity book1 = ele.getBook();
			rtd.setBookTitle(book1.getBookTitle());
			rtd.setCoverImage(book1.getCoverImage());
			rtd.setBasePrice(ele.getBasePrice());
			rtd.setDiscountPrice(ele.getBasePrice()*ele.getDiscountPercent());
			rtd.setNetPrice(rtd.getBasePrice()-rtd.getDiscountPrice());
			return rtd;
		}).collect(Collectors.toList());
		reciept.setOrderItems(recieptItems);
			return reciept;
		}
		
	}


