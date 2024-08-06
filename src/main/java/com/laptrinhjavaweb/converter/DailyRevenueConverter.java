package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.DailyRevenueDTO;
import com.laptrinhjavaweb.entity.DailyRevenueEntity;

@Component
public class DailyRevenueConverter {
	public DailyRevenueDTO toDTO(DailyRevenueEntity entity) {
		DailyRevenueDTO dto = new DailyRevenueDTO();
		dto.setDate(entity.getDate());
		dto.setTotal(entity.getTotal());
		return dto;
	}
}