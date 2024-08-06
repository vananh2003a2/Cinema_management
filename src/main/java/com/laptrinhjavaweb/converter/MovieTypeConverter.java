package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.MovieTypeDTO;
import com.laptrinhjavaweb.entity.MovieTypeEntity;

@Component
public class MovieTypeConverter {
	public MovieTypeDTO toDTO(MovieTypeEntity entity) {
		MovieTypeDTO dto = new MovieTypeDTO();
		dto.setIdMovieType(entity.getIdMovieType());
		dto.setTypeName(entity.getTypeName());

		return dto;
	}
}