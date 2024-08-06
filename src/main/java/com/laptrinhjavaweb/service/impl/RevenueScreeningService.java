package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.RevenueScreeningConverter;
import com.laptrinhjavaweb.dto.RevenueScreeningDTO;
import com.laptrinhjavaweb.entity.RevenueScreeningEntity;
import com.laptrinhjavaweb.repository.RevenueScreeningRepository;
import com.laptrinhjavaweb.service.IRevenueScreeningService;
@Service
public class RevenueScreeningService implements IRevenueScreeningService{

	@Autowired
	private RevenueScreeningRepository revenuescreeningRepo;
	
	@Autowired
	private RevenueScreeningConverter revenuescreeningConverter;
	
	@Override
	public List<RevenueScreeningDTO> revenueScreening() {
		List<RevenueScreeningDTO> lst_dto = new ArrayList<RevenueScreeningDTO>();
		List<RevenueScreeningEntity> lst_entity = revenuescreeningRepo.revenueScreening();
		for(RevenueScreeningEntity entity : lst_entity) {
			lst_dto.add(revenuescreeningConverter.toDTO(entity));
		}
		return lst_dto;
	}

}