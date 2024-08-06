package com.laptrinhjavaweb.service;

import java.util.Date;
import java.util.List;

import com.laptrinhjavaweb.dto.DailyRevenueDTO;

public interface IDailyRevenueService {
	public List<DailyRevenueDTO> findAll(Date fromDate, Date toDate);
}