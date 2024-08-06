package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.DailyRevenueConverter;
import com.laptrinhjavaweb.dto.DailyRevenueDTO;
import com.laptrinhjavaweb.entity.DailyRevenueEntity;
import com.laptrinhjavaweb.repository.DailyRevenueRepository;
import com.laptrinhjavaweb.service.IDailyRevenueService;

@Service
public class DailyRevenueService implements IDailyRevenueService{

	@Autowired
	private DailyRevenueConverter dailyrevenueConvert;
	
	@Autowired
	private DailyRevenueRepository dailyrevenueRepo;
	
	@Override
	public List<DailyRevenueDTO> findAll(Date fromDate, Date toDate) {
		List<DailyRevenueDTO> lst_dto = new ArrayList<DailyRevenueDTO>();
		List<DailyRevenueEntity> lst_entity = dailyrevenueRepo.findAllByDate(fromDate,toDate);
		for(DailyRevenueEntity entity: lst_entity) {
			lst_dto.add(dailyrevenueConvert.toDTO(entity));
		}
		return lst_dto;
	}

}