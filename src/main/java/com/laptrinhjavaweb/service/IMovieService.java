package com.laptrinhjavaweb.service;

import java.util.Date;
import java.util.List;

import com.laptrinhjavaweb.dto.MovieDTO;
import com.laptrinhjavaweb.dto.MovieEntityShowTime;
import com.laptrinhjavaweb.entity.MovieEntity;
import com.laptrinhjavaweb.entity.UserEntity;

public interface IMovieService {
	int deleteMovieIfNotUsed(int idMovie)throws Exception;
	// Get phim dang chieu va sap chieu
	List<MovieDTO> getMovieForStatus(int status) throws Exception;

	// Get phim theo slide la nhung phim nhieu ve duoc dat nhat
	List<MovieDTO> getMovieTop() throws Exception;

	List<MovieDTO> getAllMovie() throws Exception;

	MovieEntityShowTime getMovieById(int idMovie) throws Exception;

	MovieDTO getMoviesById(int idMovie);

	// phân trang user
	List<MovieEntity> findAll(int page, int size);

	long getTotalItems();
	//tìm kiếm phim
	List<MovieEntity> searchMovies(String searchValue, int movieType, Date movieDate,int page, int size);
	List<MovieEntity> searchMovieByIdType(int movieType,int page, int size);
	List<MovieEntity> searchMovieByMovieName(String searchValue,int page, int size);
	List<MovieEntity> searchMovieByMovieDate(Date movieDate,int page, int size);
	List<MovieEntity> searchMovieByIDTypeAndMovieDate( int movieType, Date movieDate, int page, int size);
	List<MovieEntity> searchMovieBySearchValueAndMovieDate( String searchValue, Date movieDate, int page, int size);
	List<MovieEntity> searchMovieByIDTypeAndSearchValue( String searchValue, int idMovieType, int page, int size);
	
	long getTotalItemsByIdType(int id);
	long getTotalItemsByDate(Date date);
	long getTotalItemsByName(String m);
	long getTotalItemsBySearch(int id, Date date, String m);
	long totalItemsByIdTypeAndMovieDate(int idMovieType, Date movieDate);
	long totalItemsByIdTypeAndSearchValue(int idMovieType, String searchValue);
	long totalItemsByMovieDateAndSearchValue(Date movieDate, String searchValue);
	
	void saveMovie(MovieDTO movieDTO) throws Exception;
	long getMaxMovieId() throws Exception;
	
	List<MovieDTO> findByStatus(int status);
	MovieDTO findOneById(int idMovie);

	List<MovieDTO> findAll() throws Exception;
	
	MovieDTO findByIdMovie(int idMovie) throws Exception;

}