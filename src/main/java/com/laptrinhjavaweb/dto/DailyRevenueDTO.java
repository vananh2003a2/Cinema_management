package com.laptrinhjavaweb.dto;

import java.util.Date;

import lombok.Data;
@Data
public class DailyRevenueDTO {
	private Date date;
	private Float total;
}