package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.RoomDTO;

public interface IRoomService {
	RoomDTO getRoomById(int id);
	List<RoomDTO> findAll();
	
	RoomDTO findfindByIdRoom(int idRoom) throws Exception;
}
