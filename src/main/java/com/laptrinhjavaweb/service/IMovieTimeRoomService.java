package com.laptrinhjavaweb.service;

import java.util.Date;
import java.util.List;

import com.laptrinhjavaweb.dto.MovieTimeRoomDTO;
import com.laptrinhjavaweb.dto.MovieTimeRoomOfChairDTO;
import com.laptrinhjavaweb.dto.TimeRange;
import com.laptrinhjavaweb.entity.RoomEntity;

public interface IMovieTimeRoomService {
	int checkMovieIsUsed(int idMovie)throws Exception;
	// Update Status Movie Time
	void updateStatusForMovieTimeRooms() throws Exception;

	List<MovieTimeRoomDTO> getAllMovie() throws Exception;

	List<MovieTimeRoomDTO> getAllGroupByIdMovie(Date date) throws Exception;

	List<MovieTimeRoomDTO> getAllGroupByIdMovie1() throws Exception;
	
	MovieTimeRoomOfChairDTO findOneByIdMovieTimeRoom(int idMovieTimeRoom) throws Exception;
	
	MovieTimeRoomDTO getTimeRoom(int id);
	
	List<Date> getDates(int idMovie) throws Exception;
	List<MovieTimeRoomDTO> getMovieTimeRoomByIdMovieAndDate(int idMovie, Date date) throws Exception;

	List<MovieTimeRoomDTO> getMovieTimeRoomByIdMovieAndCurrentDate(int idMovie) throws Exception;
	/* tìm kiếm - hiển thị suất chiếu */
	List<MovieTimeRoomDTO> finaAll() throws Exception;
	List<MovieTimeRoomDTO> findAllPageble(int offset,int limit) throws Exception;
	int count() throws Exception;
	
	List<MovieTimeRoomDTO> getNewMTR(int offset, int limit) throws Exception;
	int countNewMTR() throws Exception;
	
	List<MovieTimeRoomDTO> findMTRByStartName(String movieName, int limit,int offset) throws Exception;
	List<MovieTimeRoomDTO> searchByMovieName(String movieName,int limit,int offset) throws Exception;
	int countsearchByMovieName(String movieName) throws Exception;
	
	List<MovieTimeRoomDTO> searchByIdRoom(int idRoom,int limit,int offset) throws Exception;
	int countsearchByIdRoom(int IdRoom) throws Exception;
	
	List<MovieTimeRoomDTO> searchByDate(Date date,int limit,int offset) throws Exception;
	int countSearchByDate(Date date) throws Exception;
	
	List<MovieTimeRoomDTO> searchBy_IdRoom_Date(int idRoom, Date date ,int limit,int offset) throws Exception;
	int countSearchBy_IdRoom_Date(int idRoom, Date date) throws Exception;
	
	List<MovieTimeRoomDTO> searchBy_movieName_IdRoom(String movieName,int idRoom,int limit,int offset) throws Exception;
	int countSearchBy_movieName_IdRoom(String movieName,int idRoom) throws Exception;
	
	List<MovieTimeRoomDTO> searchBy_movieName_Date(String movieName,Date date,int limit,int offset) throws Exception;
	int countSearchBy_movieName_Date(String movieName,Date date) throws Exception;
	
	List<MovieTimeRoomDTO> searchBy_MovieName_Room_Date(String movieName, int idRoom, Date date, int limit, int offset) throws Exception;
	int countSearchBy_MovieName_Room_Date(String movieName, int idRoom, Date date) throws Exception;
	/* tìm kiếm - hiển thị suất chiếu */
	
	
	


		// Sử dụng MovieTimeRoomOfChairDTO vì cái này là DTO chung với MovieTimeRoom (do
		// cái MovieTimeRoomDTO là làm phần suất chiếu thuật toán khác nên không dùng
		// được)
		// Truyền vào RoomEntity vì bảng này có khóa ngoạij idRoom thuộc RoomEnitty
		List<MovieTimeRoomOfChairDTO> findByDateAndRoomAndStatusOrderByBeginTimeAsc(Date date, RoomEntity idRoom)
				throws Exception;

		List<MovieTimeRoomOfChairDTO> listTime(Date date, int idRoom, int idMovie, String timeInput) throws Exception;

		void saveTime(List<MovieTimeRoomOfChairDTO> lstTime, String[] checkedValues) throws Exception;

		// Lấy ra danh sách ngoại trừ suất chiếu cần cập nhật
		List<MovieTimeRoomOfChairDTO> listConvertTime(List<MovieTimeRoomOfChairDTO> lstTime,
				MovieTimeRoomOfChairDTO movieTimeRoom) throws Exception;

		MovieTimeRoomOfChairDTO findByIdMovieTimeRoom(int idMovieTimeRoom) throws Exception;

		// Hàm tìm kiếm thời gian hợp lệ để chỉnh sửa suất chiếu (nhớ truyền lstTime là
		// lst đã loại đi movieTimeRoom)
		List<TimeRange> findTimeBegin(List<MovieTimeRoomOfChairDTO> lstTime, MovieTimeRoomOfChairDTO movieTimeRoom)
				throws Exception;

		List<MovieTimeRoomOfChairDTO> listTimeRange(Date date, int idRoom, int idMovie) throws Exception;
		
		void saveTimeUpdate (String idMovieTimeRoom, String timeBegin, String timeEnd) throws Exception;
	
}