package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.TicketOfMovieConverter;
import com.laptrinhjavaweb.dto.TicketOfMovieDTO;
import com.laptrinhjavaweb.entity.TicketOfMovieEntity;
import com.laptrinhjavaweb.repository.TicketOfMovieRepository;
import com.laptrinhjavaweb.service.ITicketOfMovieService;

@Service
public class TicketOfMovieService implements ITicketOfMovieService{

	@Autowired
	private TicketOfMovieRepository tomRepo;
	
	@Autowired
	private TicketOfMovieConverter tomConverter;
	@Override
	public List<TicketOfMovieDTO> showTicketOfMovie() {
		List<TicketOfMovieDTO> lst_dto = new ArrayList<TicketOfMovieDTO>();
		List<TicketOfMovieEntity> lst_entity = tomRepo.showTicketOfMovie();
		for(TicketOfMovieEntity entity: lst_entity) {
			lst_dto.add(tomConverter.toDTO(entity));
		}
		return lst_dto;
	}

}