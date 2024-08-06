package com.laptrinhjavaweb.converter;

import java.sql.Time;
import java.time.LocalTime;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.MovieEntityShowTime;
import com.laptrinhjavaweb.dto.MovieTimeRoomDTO;
import com.laptrinhjavaweb.dto.MovieTimeRoomOfChairDTO;
import com.laptrinhjavaweb.dto.RoomDTO;
import com.laptrinhjavaweb.entity.MovieEntity;
import com.laptrinhjavaweb.entity.MovieTimeRoomEntity;
import com.laptrinhjavaweb.entity.MovieTypeEntity;
import com.laptrinhjavaweb.entity.RoomEntity;

@Component
public class MovieTimeRoomConverter {

	public MovieTimeRoomDTO toDTO(MovieTimeRoomEntity entity) {
		MovieTimeRoomDTO dto = new MovieTimeRoomDTO();
        dto.setIdMovieTimeRoom(entity.getIdMovieTimeRoom());
        MovieEntity movieEntity = entity.getIdMovie();
        RoomEntity roomEntity = entity.getIdRoom();
        
        MovieEntityShowTime movieEntityShowTime = new MovieEntityShowTime();
        BeanUtils.copyProperties(movieEntity, movieEntityShowTime, "");
        MovieTypeEntity movieTypeEntity = movieEntity.getIdMovieType();
        movieEntityShowTime.setIdMovieType(movieTypeEntity.getIdMovieType());
        movieEntityShowTime.setTypeName(movieTypeEntity.getTypeName());
        dto.setIdMovie(movieEntityShowTime);
        
        
       
        
        RoomDTO b = new RoomDTO();
        dto.setIdRoom(roomEntity.getIdRoom());
        dto.setBeginTime(entity.getBeginTime());
        dto.setEndTime(entity.getEndTime());
        dto.setDate(entity.getDate());
        dto.setStatus(entity.getStatus());
        
       
        
        return dto;
	}
	public MovieTimeRoomOfChairDTO toDTO2(MovieTimeRoomEntity entity) {
		MovieTimeRoomOfChairDTO dto = new MovieTimeRoomOfChairDTO();
		dto.setIdMovieTimeRoom(entity.getIdMovieTimeRoom());
		dto.setBeginTime(entity.getBeginTime());
		dto.setEndTime(entity.getEndTime());
//		dto.setDate(entity.getDate());
		dto.setDate(new java.util.Date(entity.getDate().getTime()));
		dto.setIdMovie(entity.getIdMovie());
		dto.setIdRoom(entity.getIdRoom());
		dto.setStatus(entity.getStatus());
		return dto;
	}
	public MovieTimeRoomOfChairDTO toDTO3(MovieTimeRoomEntity entity) {
		MovieTimeRoomOfChairDTO dto = new MovieTimeRoomOfChairDTO();
		dto.setIdMovieTimeRoom(entity.getIdMovieTimeRoom());
		// Bắt đầu phần chuyển đổi: Trừ đi 1h để khớp múi giờ với spring
		Time beginTime = entity.getBeginTime();
		Time endTime = entity.getEndTime();
		// Chuyển đổi sang LocalTime
//		LocalTime localBeginTime = beginTime.toLocalTime();
//		LocalTime localEndTime = endTime.toLocalTime();
//
//		// Trừ đi 1 giờ từ thời gian
//		LocalTime adjustedBeginTime = localBeginTime.minusHours(1);
//		LocalTime adjustedEndTime = localEndTime.minusHours(1);
//
//		// Chuyển đổi lại sang java.sql.Time
//		Time adjustedSqlBeginTime = Time.valueOf(localBeginTime);
//		Time adjustedSqlEndTime = Time.valueOf(localEndTime);
//		// Kết thúc phần chuyển đổi
//		// Đặt giá trị đã điều chỉnh vào DTO
//		dto.setBeginTime(entity.getBeginTime());
//		dto.setEndTime(entity.getEndTime());
		dto.setBeginTime(beginTime);
		dto.setEndTime(endTime);
		dto.setDate(entity.getDate());
		dto.setIdMovie(entity.getIdMovie());
		dto.setIdRoom(entity.getIdRoom());
		dto.setStatus(entity.getStatus());
		return dto;
	}

	public MovieTimeRoomEntity toEntity(MovieTimeRoomOfChairDTO dto) {
		MovieTimeRoomEntity entity = new MovieTimeRoomEntity();
		entity.setIdMovieTimeRoom(dto.getIdMovieTimeRoom());
//		// Bắt đầu phần chuyển đổi: Trừ đi 1h để khớp múi giờ với spring
//		Time beginTime = dto.getBeginTime();
//		Time endTime = dto.getEndTime();
//		// Chuyển đổi sang LocalTime
//		LocalTime localBeginTime = beginTime.toLocalTime();
//		LocalTime localEndTime = endTime.toLocalTime();
//
//		// Trừ đi 1 giờ từ thời gian
//		LocalTime adjustedBeginTime = localBeginTime.plusHours(1);
//		LocalTime adjustedEndTime = localEndTime.plusHours(1);
//
//		// Chuyển đổi lại sang java.sql.Time
//		Time adjustedSqlBeginTime = Time.valueOf(adjustedBeginTime);
//		Time adjustedSqlEndTime = Time.valueOf(adjustedEndTime);
//		// Kết thúc phần chuyển đổi
//		// Đặt giá trị đã điều chỉnh vào entity
		entity.setBeginTime(dto.getBeginTime());
		entity.setEndTime(dto.getEndTime());
		entity.setDate(dto.getDate());
		entity.setIdMovie(dto.getIdMovie());
		entity.setIdRoom(dto.getIdRoom());
		entity.setStatus(dto.getStatus());
		return entity;
	}
}