package com.laptrinhjavaweb.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.MovieTypeConverter;
import com.laptrinhjavaweb.dto.MovieTypeDTO;
import com.laptrinhjavaweb.entity.MovieTypeEntity;
import com.laptrinhjavaweb.repository.MovieTypeRepository;
import com.laptrinhjavaweb.service.IMovieTypeService;
@Service
public class MovieTypeService implements IMovieTypeService{
	@Autowired 
	private MovieTypeRepository movieTypeRepository;
	@Autowired 
	private MovieTypeConverter convert;
	


	@Override
	public List<MovieTypeDTO> getAllMovieType() throws Exception {
		List<MovieTypeEntity> listEntity = movieTypeRepository.findAll();
		List<MovieTypeDTO> listDto = new ArrayList<MovieTypeDTO>(); 
		for(MovieTypeEntity entity : listEntity) {
			MovieTypeDTO dto = new MovieTypeDTO();
			dto = convert.toDTO(entity);
			listDto.add(dto);
		}
		
		return listDto;
	}

	@Override
	public MovieTypeDTO getMovieTypeById(int idMovieType) throws Exception {
		MovieTypeEntity entity = movieTypeRepository.findByIdMovie(idMovieType);
		if (entity == null) {
	        throw new Exception("Không tìm thấy loại phim với id: " + idMovieType);
	    }
		
		MovieTypeDTO dto = convert.toDTO(entity);
		
		return dto;
	}

	@Override
	public List<MovieTypeDTO> findAll() {
		List<MovieTypeDTO> lst_dto = new ArrayList<MovieTypeDTO>();
		List<MovieTypeEntity> lst_entity = movieTypeRepository.findAll();
		for(MovieTypeEntity entity: lst_entity) {
			lst_dto.add(convert.toDTO(entity));
		}
		return lst_dto;
	}
	
}
