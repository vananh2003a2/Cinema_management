package com.laptrinhjavaweb.dto;

import java.sql.Time;
import java.util.Date;

import com.laptrinhjavaweb.entity.MovieEntity;
import com.laptrinhjavaweb.entity.RoomEntity;

import lombok.Data;
@Data
public class MovieTimeRoomOfChairDTO {
	private int idMovieTimeRoom;
	private MovieEntity idMovie;
	private RoomEntity idRoom;
	private Time beginTime;
	private Time endTime;
	private Date date;
	private int status;
}