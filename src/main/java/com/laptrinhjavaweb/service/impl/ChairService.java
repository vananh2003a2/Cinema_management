package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.ChairConverter;
import com.laptrinhjavaweb.dto.ChairDTO;
import com.laptrinhjavaweb.dto.ComboDTO;
import com.laptrinhjavaweb.entity.ChairEntity;
import com.laptrinhjavaweb.entity.ComboEntity;
import com.laptrinhjavaweb.repository.ChairRepository;
import com.laptrinhjavaweb.service.IChairService;

@Service
public class ChairService implements IChairService {

	@Autowired
	private ChairRepository chairRepo;

	@Autowired
	private ChairConverter chairConverter;

	@Override
	public List<ChairDTO> findChairByIdRoom(int idRoom) {
		List<ChairDTO> lst_chair = new ArrayList<ChairDTO>();
		List<ChairEntity> chairsEntity = chairRepo.findChairByIdRoom(idRoom);
		for (ChairEntity entity : chairsEntity) {
			lst_chair.add(chairConverter.toDTO(entity));
		}
		return lst_chair;
	}

	@Override
	public List<ChairDTO> getBookedChairs(int idMovieTimeRoom) {
		List<ChairDTO> lst_chair = new ArrayList<ChairDTO>();
		List<ChairEntity> chairsEntity = chairRepo.getBookedChairs(idMovieTimeRoom);
		for (ChairEntity entity : chairsEntity) {
			lst_chair.add(chairConverter.toDTO(entity));
		}
		return lst_chair;
	}

	@Override
	public List<ChairDTO> getUnbookedChairs(int idRoom, int idMovieTimeRoom) {
		List<ChairDTO> lst_chair = new ArrayList<ChairDTO>();
		List<ChairEntity> chairsEntity = chairRepo.getUnbookedChairs(idRoom, idMovieTimeRoom);
		for (ChairEntity entity : chairsEntity) {
			lst_chair.add(chairConverter.toDTO(entity));
		}
		return lst_chair;
	}

	@Override
	public ChairDTO findOneById(int idCinemaChair) {
		ChairDTO dto = chairConverter.toDTO(chairRepo.findOne(idCinemaChair));
		return dto;
	}

	@Override
	public ChairDTO getChairbyid(int id) {
		ChairDTO chairDTO = new ChairDTO();
		chairDTO = chairConverter.toDTO(chairRepo.findByIdCinemaChair(id));
		return chairDTO;
	}

	@Override
	public List<ChairDTO> getchair() throws Exception {
		List<ChairEntity> lst = chairRepo.findAll();
        List<ChairDTO> lstDTO = new ArrayList<>();
        for (ChairEntity chair : lst) {
        	ChairDTO dto = chairConverter.toDTO(chair);
            lstDTO.add(dto);
        }
        return lstDTO;
	}

	@Override
	public List<ChairDTO> getchairById(int id) throws Exception {
		List<ChairEntity> lst = chairRepo.findChairByIdChair(id);
        List<ChairDTO> lstDTO = new ArrayList<>();
        for (ChairEntity chair : lst) {
        	ChairDTO dto = chairConverter.toDTO(chair);
            lstDTO.add(dto);
        }
        return lstDTO;
	}
}