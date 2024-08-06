package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.RevenueMovieDTO;
import com.laptrinhjavaweb.entity.RevenueMovieEntity;

@Component
public class RevenueMovieConverter {
	public RevenueMovieDTO toDTO(RevenueMovieEntity entity) {
		RevenueMovieDTO dto = new RevenueMovieDTO();
		dto.setIdMovie(entity.getIdMovie());
		dto.setMovieName(entity.getMovieName());
		dto.setTotal(entity.getTotal());
		return dto;
	}
}