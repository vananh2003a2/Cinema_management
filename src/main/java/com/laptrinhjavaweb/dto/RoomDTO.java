package com.laptrinhjavaweb.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class RoomDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idRoom;
	private String RoomCode;
	private int chairTotal;
//	private List<ChairEntity> chairs= new ArrayList<ChairEntity>();
//	private List<MovieTimeRoomEntity> movieTimeRooms = new ArrayList<MovieTimeRoomEntity>();
}
