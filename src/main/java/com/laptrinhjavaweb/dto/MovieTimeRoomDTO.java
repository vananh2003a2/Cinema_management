package com.laptrinhjavaweb.dto;

import java.util.Date;
import java.io.Serializable;
import java.sql.Time;

import com.laptrinhjavaweb.entity.MovieEntity;
import com.laptrinhjavaweb.entity.RoomEntity;

import lombok.Data;

@Data
public class MovieTimeRoomDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idMovieTimeRoom;
    private MovieEntityShowTime idMovie;
    private int idRoom;
    private Time beginTime;
    private Time endTime;
    private Date date;
    private int status;

}


