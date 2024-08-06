package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.ChairDTO;
import com.laptrinhjavaweb.dto.ComboDTO;

public interface IChairService {
	List<ChairDTO> findChairByIdRoom(int idRoom);
	List<ChairDTO> getBookedChairs(int idMovieTimeRoom);
	List<ChairDTO> getUnbookedChairs(int idRoom,int idMovieTimeRoom);
	ChairDTO findOneById(int idCinemaChair);
	
	ChairDTO getChairbyid(int id);
	
	//lấy danh sách combo
	List<ChairDTO> getchair() throws Exception;
	List<ChairDTO> getchairById(int id) throws Exception;
}