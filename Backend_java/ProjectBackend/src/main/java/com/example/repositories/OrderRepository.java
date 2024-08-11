package com.example.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.OrderEntity;
import com.example.entities.OrderItemEntity;
import com.example.entities.PublisherEntity;
import com.example.entities.UserEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
@Query("Select o.orderItems from OrderEntity o where o.customer.email=:email")
public List<OrderItemEntity> bookIdsofBooksPurchasedByCustomer(String email);
}
