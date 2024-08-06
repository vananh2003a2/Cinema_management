package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.RoomConverter;
import com.laptrinhjavaweb.dto.RoomDTO;
import com.laptrinhjavaweb.entity.RoomEntity;
import com.laptrinhjavaweb.repository.RoomRepository;
import com.laptrinhjavaweb.service.IRoomService;
@Service
public class RoomService implements IRoomService{
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private RoomConverter roomConverter;
	@Override
	public RoomDTO getRoomById(int id) {
		RoomDTO roomDTO = new RoomDTO();
		roomDTO=roomConverter.toDTO(roomRepository.findByidRoom(id));
		return roomDTO;
	}
	@Override
	public List<RoomDTO> findAll() {
		List<RoomDTO> lst_dto = new ArrayList<RoomDTO>();
		List<RoomEntity> lst_entity = roomRepository.findAll();
		for(RoomEntity entity: lst_entity) {
			lst_dto.add(roomConverter.toDTO(entity));
		}
		return lst_dto;
	}
	@Override
	public RoomDTO findfindByIdRoom(int idRoom) throws Exception {
		RoomEntity roomEntity = roomRepository.findByIdRoom(idRoom);
		RoomDTO roomDTO = roomConverter.toDTO(roomEntity);
		return roomDTO;
	}

}
