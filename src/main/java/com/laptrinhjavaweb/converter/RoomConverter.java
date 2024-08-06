package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.RoomDTO;
import com.laptrinhjavaweb.entity.RoomEntity;


@Component
public class RoomConverter {
	public RoomDTO toDTO(RoomEntity entity) {
		RoomDTO dto = new RoomDTO();
		dto.setIdRoom(entity.getIdRoom());
		dto.setRoomCode(entity.getRoomCode());
		dto.setChairTotal(entity.getChairTotal());
		return dto;
	}
}
