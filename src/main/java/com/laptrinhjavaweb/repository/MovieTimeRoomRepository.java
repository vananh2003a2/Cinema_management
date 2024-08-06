package com.laptrinhjavaweb.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.entity.MovieTimeRoomEntity;
import com.laptrinhjavaweb.entity.RoomEntity;

@Transactional
public interface MovieTimeRoomRepository extends JpaRepository<MovieTimeRoomEntity, Integer> {

	@Query(value = "SELECT COUNT(*) FROM movietimeroom where idMovie = ?",nativeQuery = true)
	int checkMovieIsUsed(int idMovie) throws Exception;
	
	List<MovieTimeRoomEntity> findAll();

	@Query("SELECT m FROM MovieTimeRoomEntity m GROUP BY m.idMovie")
	List<MovieTimeRoomEntity> findAllGroupByIdMovie();

	@Query("SELECT m.beginTime, m.endTime FROM MovieTimeRoomEntity m WHERE m.idMovie = ?1")
	List<Object[]> findStartAndEndTimeByMovieId(int movieId);

	List<MovieTimeRoomEntity> findAllByOrderByBeginTimeAsc();

	@Query("SELECT m FROM MovieTimeRoomEntity m WHERE m.date = :date GROUP BY m.idMovie")
	List<MovieTimeRoomEntity> findAllByDateGroupByIdMovie(@Param("date") Date date);

	@Query(value = "SELECT * FROM movietimeroom WHERE idMovieTimeRoom = ? ORDER BY beginTime ASC", nativeQuery = true)
	MovieTimeRoomEntity findOneByIdMovieTimeRoom(int idMovieTimeRoom) throws Exception;
	
	MovieTimeRoomEntity findByIdMovieTimeRoom(int idMovieTimeRoom);
	
	
	/* Hiển thị các ngày chiếu bắt đầu từ ngày hiện tại */
	@Query(value = "CALL ShowTimesAfterToday(?)",nativeQuery = true)
	List<Date> getDates(int idMovie) throws Exception;
	@Query(value = "SELECT * FROM movietimeroom WHERE idMovie = ? AND date = ? ORDER BY beginTime ASC",nativeQuery = true)
	List<MovieTimeRoomEntity> getMovieTimeRoomByIdMovieAndDate(int idMovie, Date date) throws Exception;

	@Query(value = "CALL ShowTimes(?);",nativeQuery = true)
	List<MovieTimeRoomEntity> getMovieTimeRoomByIdMovieAndCurrentDate(int idMovie) throws Exception;

	/* tìm kiếm - hiển thị suất chiếu */
	@Query(value = "SELECT * FROM movietimeroom ORDER BY date ASC, beginTime ASC LIMIT ?,?",nativeQuery = true)
	List<MovieTimeRoomEntity> findAllPageble(int offset,int limit) throws Exception;
	
	@Query(value = "CALL show_new_screening(?,?)",nativeQuery = true)
	List<MovieTimeRoomEntity> getNewMTR(int limit, int offset) throws Exception;
	@Query(value = "CALL count_show_new_screening()",nativeQuery = true)
	int countNewMTR() throws Exception;
	
	@Query(value = "CALL search_screening1(?,?,?)",nativeQuery = true)
	List<MovieTimeRoomEntity> findMTRByStartName(String movieName, int limit,int offset) throws Exception;
	
	@Query(value = "CALL search_screening(?,?,?)",nativeQuery = true)
	List<MovieTimeRoomEntity> searchByMovieName(String movieName,int limit,int offset) throws Exception;
	@Query(value = "CALL count_search_screening(?)",nativeQuery = true)
	int countsearchByMovieName(String movieName) throws Exception;
	
	@Query(value = "SELECT * FROM movietimeroom where idRoom = ? LIMIT ? OFFSET ?",nativeQuery = true)
	List<MovieTimeRoomEntity> searchByIdRoom(int idRoom,int limit,int offset) throws Exception;
	@Query(value = "SELECT COUNT(*) FROM movietimeroom where idRoom = ?",nativeQuery = true)
	int countsearchByIdRoom(int idRoom) throws Exception;
	
	@Query(value = "SELECT * FROM movietimeroom where date = ? LIMIT ? OFFSET ?",nativeQuery = true)
	List<MovieTimeRoomEntity> searchByDate(Date date,int limit,int offset) throws Exception;
	@Query(value = "SELECT COUNT(*) FROM movietimeroom where date = ?",nativeQuery = true)
	int countSearchByDate(Date date) throws Exception;
	
	@Query(value = "SELECT * FROM movietimeroom where idRoom = ? AND date = ? LIMIT ? OFFSET ?",nativeQuery = true)
	List<MovieTimeRoomEntity> searchBy_IdRoom_Date(int idRoom, Date date ,int limit,int offset) throws Exception;
	@Query(value = "SELECT COUNT(*) FROM movietimeroom where idRoom = ? AND date = ?",nativeQuery = true)
	int countSearchBy_IdRoom_Date(int idRoom, Date date) throws Exception;
	
	@Query(value = "CALL search_screening_movieName_room(?,?,?,?);",nativeQuery = true)
	List<MovieTimeRoomEntity> searchBy_movieName_IdRoom(String movieName,int idRoom,int limit,int offset) throws Exception;
	@Query(value = "CALL count_search_screening_movieName_room(?,?)",nativeQuery = true)
	int countSearchBy_movieName_IdRoom(String movieName,int idRoom) throws Exception;
	
	@Query(value = "CALL search_screening_movieName_date(?,?,?,?);",nativeQuery = true)
	List<MovieTimeRoomEntity> searchBy_movieName_Date(String movieName,Date date,int limit,int offset) throws Exception;
	@Query(value = "CALL count_search_screening_movieName_date(?,?);",nativeQuery = true)
	int countSearchBy_movieName_Date(String movieName,Date date) throws Exception;
	
	
	@Query(value = "CALL search_screening_by_room_movieName_date(?,?,?,?,?)",nativeQuery = true)
	List<MovieTimeRoomEntity> searchBy_MovieName_Room_Date(String movieName, int idRoom, Date date, int limit, int offset) throws Exception;
	@Query(value = "CALL count_search_screening_by_room_movieName_date(?,?,?)",nativeQuery = true)
	int countSearchBy_MovieName_Room_Date(String movieName, int idRoom, Date date) throws Exception;
	/* tìm kiếm - hiển thị suất chiếu */
	
	
	
	
	// Status =0 => đã chiếu, đang chiếu status=1 => chưa chiếu (defau
//		@Modifying
//		//@Query("UPDATE MovieTimeRoomEntity  SET status = 0 WHERE beginTime <= CURRENT_TIMESTAMP AND idMovieTimeRoom > 0 AND date = CURRENT_DATE() ")
//		void updateStatusForMovieTimeRooms();


		// Câu lệnh dùng để get ra suất chiếu được sắp xếp theo giờ bắt đầu theo date và
		// idRoom được truyền vào
		 @Query("SELECT m FROM MovieTimeRoomEntity m " +
		           "WHERE m.date = :date " +
		           "AND m.idRoom = :idRoom " +
		           "AND m.status = 1 " +
		           "ORDER BY m.beginTime ASC")
		    List<MovieTimeRoomEntity> findByDateAndRoomAndStatusOrderByBeginTimeAsc(@Param("date") Date date,  @Param("idRoom") RoomEntity idRoom);
}