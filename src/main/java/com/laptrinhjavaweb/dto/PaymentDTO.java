package com.laptrinhjavaweb.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PaymentDTO implements Serializable{
	private String status;
	private String message;
	private String url;
}
