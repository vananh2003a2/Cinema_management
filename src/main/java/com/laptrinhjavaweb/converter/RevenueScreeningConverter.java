package com.laptrinhjavaweb.converter;

import java.sql.Time;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.RevenueScreeningDTO;
import com.laptrinhjavaweb.entity.RevenueScreeningEntity;

@Component
public class RevenueScreeningConverter {
	public RevenueScreeningDTO toDTO(RevenueScreeningEntity entity) {
		RevenueScreeningDTO dto = new RevenueScreeningDTO();
		Time entityTime = entity.getBeginTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String timeString = sdf.format(entityTime);
		dto.setBeginTime(timeString);
		System.out.println(timeString);
		dto.setSoVe(entity.getSoVe());
		return dto;
		
	}
}