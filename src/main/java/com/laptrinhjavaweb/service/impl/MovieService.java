package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.MovieConverter;
import com.laptrinhjavaweb.converter.MovieEntityShowTimeConverter;
import com.laptrinhjavaweb.dto.MovieDTO;
import com.laptrinhjavaweb.dto.MovieEntityShowTime;
import com.laptrinhjavaweb.entity.MovieEntity;
import com.laptrinhjavaweb.repository.MovieRepository;
import com.laptrinhjavaweb.repository.MovieTimeRoomRepository;
import com.laptrinhjavaweb.service.IMovieService;

@Service
public class MovieService implements IMovieService {

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private MovieTimeRoomRepository movieTimeRoomRepository;

	@Autowired
	private MovieConverter movieConverter;

	@Autowired
	private MovieEntityShowTimeConverter movieShowConverter;
	
	@Override
	public List<MovieDTO> getMovieForStatus(int status) throws Exception {
		List<MovieEntity> lstMovieEntity = movieRepository.findByStatus(status);
		List<MovieDTO> lstMovieDTO = new ArrayList<MovieDTO>();
		for (MovieEntity movieEntity : lstMovieEntity) {
			MovieDTO dto = new MovieDTO();
			dto = movieConverter.toDTO(movieEntity);
			lstMovieDTO.add(dto);
		}
		return lstMovieDTO;
	}

	@Override
	public List<MovieDTO> getMovieTop() throws Exception {
		List<MovieEntity> lstMovieEntity = movieRepository.getMovieTop();
		List<MovieDTO> lstMovieDTO = new ArrayList<MovieDTO>();
		for (MovieEntity movieEntity : lstMovieEntity) {
			MovieDTO dto = new MovieDTO();
			dto = movieConverter.toDTO(movieEntity);
			lstMovieDTO.add(dto);
		}
		return lstMovieDTO;
	}

	@Override
	public List<MovieDTO> getAllMovie() throws Exception {
		List<MovieEntity> lstMovieEntity = movieRepository.findAll();
		List<MovieDTO> lstMovieDTO = new ArrayList<MovieDTO>();
		for (MovieEntity movieEntity : lstMovieEntity) {
			MovieDTO dto = new MovieDTO();
			dto = movieConverter.toDTO(movieEntity);
			lstMovieDTO.add(dto);
		}
		return lstMovieDTO;
	}

	@Override
	public MovieEntityShowTime getMovieById(int idMovie) throws Exception {
		MovieEntity movieEntity = movieRepository.findByIdMovie(idMovie);
		if (movieEntity == null) {
			throw new Exception("Không tìm thấy bộ phim với id: " + idMovie);
		}
		MovieEntityShowTime movieShowDTO = movieShowConverter.toDTO(movieEntity);
		return movieShowDTO;
	}
	@Override
	public MovieDTO getMoviesById(int idMovie) {
		MovieDTO movieDTO = new MovieDTO();
		movieDTO = movieConverter.toDTO(movieRepository.findByIdMovie(idMovie));
		return movieDTO;
	}

	@Override
	public List<MovieEntity> findAll(int page, int size) {
		int offset = page * size;
		return movieRepository.findAll(size, offset);
	}

	@Override
	public long getTotalItems() {
		return movieRepository.totalItems();
	}

	@Override
	public List<MovieEntity> searchMovies(String searchValue, int movieType, Date movieDate,int page, int size) {
		int offset = page * size;
		return movieRepository.searchMovies(searchValue, movieType, movieDate, size,offset);
    }

	@Override
	public List<MovieEntity> searchMovieByIdType(int movieType, int page, int size) {
		// TODO Auto-generated method stub
		int offset = page * size;
		return movieRepository.searchMovieByIDType(movieType,  size,offset);
	}

	@Override
	public List<MovieEntity> searchMovieByMovieName(String searchValue, int page, int size) {
		// TODO Auto-generated method stub
		int offset = page * size;
		return movieRepository.searchMovieByMovieName(searchValue, size,offset);
	}

	@Override
	public List<MovieEntity> searchMovieByMovieDate(Date movieDate, int page, int size) {
		// TODO Auto-generated method stub
		int offset = page * size;
		return movieRepository.searchMovieByMovieDate(movieDate, size,offset);
	}

	@Override
	public List<MovieEntity> searchMovieByIDTypeAndMovieDate(int movieType, Date movieDate, int page, int size) {
		int offset = page * size;
		return movieRepository.searchMovieByIDTypeAndMovieDate(movieType, movieDate, size, offset);
	}

	@Override
	public List<MovieEntity> searchMovieBySearchValueAndMovieDate(String searchValue, Date movieDate, int page,
			int size) {
		int offset = page * size;
		return movieRepository.searchMovieBySearchValueAndMovieDate(searchValue, movieDate, size, offset);
	}

	@Override
	public List<MovieEntity> searchMovieByIDTypeAndSearchValue(String searchValue, int idMovieType, int page,
			int size) {
		int offset = page * size;
		return movieRepository.searchMovieByIDTypeAndSearchValue(searchValue, idMovieType, size, offset);
	}

	@Override
	public long getTotalItemsByIdType(int id) {
		
		return movieRepository.totalItemsByIdType(id);
	}

	@Override
	public long getTotalItemsByDate(Date date) {
		// TODO Auto-generated method stub
		return movieRepository.totalItemsByMovieDate(date);
	}

	@Override
	public long getTotalItemsByName(String m) {
		// TODO Auto-generated method stub
		return movieRepository.totalItemsByMovieName(m);
	}

	@Override
	public long getTotalItemsBySearch(int id, Date date, String m) {
		// TODO Auto-generated method stub
		return movieRepository.totalItemsBySearch(id, m, date);
	}

	@Override
	public long totalItemsByIdTypeAndMovieDate(int idMovieType, Date movieDate) {
		// TODO Auto-generated method stub
		return movieRepository.totalItemsByIdTypeAndMovieDate(idMovieType, movieDate);
	}

	@Override
	public long totalItemsByIdTypeAndSearchValue(int idMovieType, String searchValue) {
		// TODO Auto-generated method stub
		return movieRepository.totalItemsByIdTypeAndSearchValue(idMovieType, searchValue);
	}

	@Override
	public long totalItemsByMovieDateAndSearchValue(Date movieDate, String searchValue) {
		// TODO Auto-generated method stub
		return movieRepository.totalItemsByMovieDateAndSearchValue(movieDate, searchValue);
	}

	@Override
	public void saveMovie(MovieDTO movieDTO) throws Exception {
		java.util.Date utilDate = movieDTO.getMovieDate();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		movieDTO.setMovieDate(sqlDate);
		MovieEntity movieEntity = movieConverter.toEntity(movieDTO);
		movieRepository.save(movieEntity);
		
	}

	@Override
	public long getMaxMovieId() throws Exception {
		long maxId = movieRepository.findMaxMovieId();
		return maxId;
	}

	@Override
	public List<MovieDTO> findByStatus(int status) {
		List<MovieEntity> moviesEntity = movieRepository.findByStatus(status);
		List<MovieDTO> moviesDTO = new ArrayList<MovieDTO>();
		for (MovieEntity entity : moviesEntity) {
			moviesDTO.add(movieConverter.toDTO(entity));
		}
		return moviesDTO;
	}

	@Override
	public MovieDTO findOneById(int idMovie) {
		MovieDTO movie = new MovieDTO();
		movie = movieConverter.toDTO(movieRepository.findOne(idMovie));
		return movie ;
	}

	@Override
	public List<MovieDTO> findAll() throws Exception {
		List<MovieEntity> lstMovieEntity = movieRepository.findAll();
		List<MovieDTO> lstMovieDTO = new ArrayList<MovieDTO>();
		for (MovieEntity movieEntity : lstMovieEntity) {
			MovieDTO dto = new MovieDTO();
			dto = movieConverter.toDTO(movieEntity);
			lstMovieDTO.add(dto);
		}
		return lstMovieDTO;
	}

	@Override
	public MovieDTO findByIdMovie(int idMovie) throws Exception {
		MovieEntity movieEntity= movieRepository.findByIdMovie(idMovie);
		MovieDTO movieDTO = movieConverter.toDTO(movieEntity);
		return movieDTO;
	}

	@Override
	public int deleteMovieIfNotUsed(int idMovie) throws Exception {
		int result = movieTimeRoomRepository.checkMovieIsUsed(idMovie);
		if(result>0) {
			return 0;
		}else {
			movieRepository.deleteMovie(idMovie);
			return 1;
		}
	}

	

	
}