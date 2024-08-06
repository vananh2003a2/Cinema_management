package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.RevenueMovieConverter;
import com.laptrinhjavaweb.dto.RevenueMovieDTO;
import com.laptrinhjavaweb.entity.RevenueMovieEntity;
import com.laptrinhjavaweb.repository.RevenueMovieRepository;
import com.laptrinhjavaweb.service.IRevenueMovieService;

@Service
public class RevenueMovieService implements IRevenueMovieService{

	@Autowired
	private RevenueMovieRepository revenuemovieRepo;
	
	@Autowired
	private RevenueMovieConverter revenuemovieConverter;
	
	@Override
	public List<RevenueMovieDTO> revenueAllMovie() {
		List<RevenueMovieDTO> lst_dto = new ArrayList<RevenueMovieDTO>();
		List<RevenueMovieEntity> lst_entity = revenuemovieRepo.revenueAllMovie();
		for(RevenueMovieEntity entity : lst_entity) {
			lst_dto.add(revenuemovieConverter.toDTO(entity));
		}
		return lst_dto;
	}

}