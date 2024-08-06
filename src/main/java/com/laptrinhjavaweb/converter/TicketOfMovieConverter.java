package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.TicketOfMovieDTO;
import com.laptrinhjavaweb.entity.TicketOfMovieEntity;

@Component
public class TicketOfMovieConverter {
public TicketOfMovieDTO toDTO(TicketOfMovieEntity entity) {
	TicketOfMovieDTO dto =  new TicketOfMovieDTO();
	dto.setIdMovie(entity.getIdMovie());
	dto.setMovieDate(entity.getMovieDate());
	dto.setMovieName(entity.getMovieName());
	dto.setStatus(entity.getStatus());
	dto.setTotal(entity.getTotal());
	return dto;
}
}