package com.example.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecieptItemsDTO {
private String bookTitle;
private String coverImage;
private double basePrice;
private double discountPrice;
private double netPrice;
private LocalDateTime rentStartDate;
private LocalDateTime rentLastDate;
private int noOfDaysRented;
}
