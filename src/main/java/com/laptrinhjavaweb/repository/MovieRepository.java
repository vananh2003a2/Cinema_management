package com.laptrinhjavaweb.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laptrinhjavaweb.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
	
	@Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE movie SET status = 2 WHERE idMovie = ?1")
    void deleteMovie(int idMovie);
	
	List<MovieEntity> findByStatus(int status);

	@Query(nativeQuery = true, value = "CALL moviesTop()")
	List<MovieEntity> getMovieTop();

	MovieEntity findByIdMovie(int idMovie);

	// phân trang movie
	@Query(value = "SELECT * FROM movie WHERE status = 1 OR status = 0 ORDER BY idMovie LIMIT ?1 OFFSET ?2", nativeQuery = true)
	List<MovieEntity> findAll(int limit, int offset);

	@Query(value = "SELECT COUNT(*) FROM movie WHERE status = 1 or status = 0", nativeQuery = true)
	long totalItems();
	//tìm kiếm
	@Query(value = "SELECT * FROM movie WHERE (idMovieType IS NULL OR idMovieType = :movieType) AND (movieDate IS NULL OR movieDate = :movieDate) AND (movieName IS NULL OR movieName LIKE %:searchValue%) ORDER BY idMovie LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<MovieEntity> searchMovies(@Param("searchValue") String searchValue, @Param("movieType") Integer movieType, @Param("movieDate") Date movieDate,@Param("limit") int limit,@Param("offset") int offset);
	
	@Query(value = "SELECT * FROM movie WHERE (idMovieType IS NULL OR idMovieType = ?1) ORDER BY idMovie LIMIT ?2 OFFSET ?3", nativeQuery = true)
    List<MovieEntity> searchMovieByIDType( int movieType, int limit, int offset);
	
	@Query(value = "SELECT * FROM movie WHERE (movieDate IS NULL OR movieDate = :movieDate) ORDER BY idMovie LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<MovieEntity> searchMovieByMovieDate( @Param("movieDate") Date movieDate, @Param("limit") int limit,@Param("offset") int offset);
	
	@Query(value = "SELECT * FROM movie WHERE (movieName IS NULL OR movieName LIKE %:searchValue%) ORDER BY idMovie LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<MovieEntity> searchMovieByMovieName( @Param("searchValue") String searchValue, @Param("limit") int limit,@Param("offset") int offset);
	
	@Query(value = "SELECT * FROM movie WHERE (idMovieType IS NULL OR idMovieType = ?1) AND (movieDate IS NULL OR movieDate = ?2) ORDER BY idMovie LIMIT ?3 OFFSET ?4", nativeQuery = true)
    List<MovieEntity> searchMovieByIDTypeAndMovieDate( int movieType, Date movieDate, int limit, int offset);
	
	@Query(value = "SELECT * FROM movie WHERE (movieName IS NULL OR movieName LIKE %?1%) AND (movieDate IS NULL OR movieDate = ?2) ORDER BY idMovie LIMIT ?3 OFFSET ?4", nativeQuery = true)
    List<MovieEntity> searchMovieBySearchValueAndMovieDate( String searchValue, Date movieDate, int limit, int offset);
	
	@Query(value = "SELECT * FROM movie WHERE (movieName IS NULL OR movieName LIKE %?1%) AND (idMovieType IS NULL OR idMovieType = ?2) ORDER BY idMovie LIMIT ?3 OFFSET ?4", nativeQuery = true)
    List<MovieEntity> searchMovieByIDTypeAndSearchValue( String searchValue, int idMovieType, int limit, int offset);
	
	@Query(value = "SELECT COUNT(*) FROM movie WHERE idMovieType = ?1", nativeQuery = true)
	long totalItemsByIdType(int idMovieType);
	
	@Query(value = "SELECT COUNT(*) FROM movie WHERE movieName  LIKE %?1%", nativeQuery = true)
	long totalItemsByMovieName(String movieName);
	
	@Query(value = "SELECT COUNT(*) FROM movie WHERE movieDate = ?1", nativeQuery = true)
	long totalItemsByMovieDate(Date movieDate );
	
	@Query(value = "SELECT COUNT(*) FROM movie WHERE idMovieType = ?1 AND movieName  LIKE %?2% AND movieDate = ?3", nativeQuery = true)
	long totalItemsBySearch(int idMovieType,String movieName,Date movieDate);
	
	@Query(value = "SELECT COUNT(*) FROM movie WHERE idMovieType = ?1 AND movieDate = ?2", nativeQuery = true)
	long totalItemsByIdTypeAndMovieDate(int idMovieType, Date movieDate);
	
	@Query(value = "SELECT COUNT(*) FROM movie WHERE idMovieType = ?1 AND movieName  LIKE %?2%", nativeQuery = true)
	long totalItemsByIdTypeAndSearchValue(int idMovieType, String searchValue);
	
	@Query(value = "SELECT COUNT(*) FROM movie WHERE movieDate = ?1 AND movieName  LIKE %?2%", nativeQuery = true)
	long totalItemsByMovieDateAndSearchValue(Date movieDate, String searchValue);
	
	// Tìm id lớn nhất của bộ phim
    @Query("SELECT MAX(m.idMovie) FROM MovieEntity m")
    Integer findMaxMovieId();
	
	
}