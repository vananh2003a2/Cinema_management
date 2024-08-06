package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.MovieTypeDTO;
import com.laptrinhjavaweb.entity.MovieTypeEntity;

public interface IMovieTypeService {

	
	List<MovieTypeDTO> getAllMovieType() throws Exception;
	MovieTypeDTO getMovieTypeById(int idMovieType) throws Exception;
	
	public List<MovieTypeDTO> findAll();
}
