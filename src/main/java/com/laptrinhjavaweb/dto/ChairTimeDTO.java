package com.laptrinhjavaweb.dto;

import com.laptrinhjavaweb.entity.ChairEntity;
import com.laptrinhjavaweb.entity.MovieTimeRoomEntity;

import lombok.Data;

@Data
public class ChairTimeDTO {
	private int idChairTime;
	private ChairEntity chair;
	private MovieTimeRoomEntity movieTimeRoom;
	private int status;
}