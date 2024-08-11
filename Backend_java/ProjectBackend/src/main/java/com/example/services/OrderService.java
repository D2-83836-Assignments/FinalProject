package com.example.services;

import com.example.dto.OrderAddDTO;
import com.example.dto.RecieptDTO;

public interface OrderService {
public RecieptDTO placeOrder(OrderAddDTO orderDto);
}
