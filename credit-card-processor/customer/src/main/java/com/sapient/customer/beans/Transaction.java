package com.sapient.customer.beans;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.Link;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.sapient.customer.constants.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Transaction {
	@Id
	private String transactionId;
	private String ccNumber;
	private Double transactionAmount;
	private String transactionType;
	private String transactionMode;
	private LocalDate transactionDate;
	private LocalTime transactionTime;
	private String transactionLocation;
	private String transactionIP;
	private String transactionStatus;
	private String merchantId;
	private String transactionDescription;
}
