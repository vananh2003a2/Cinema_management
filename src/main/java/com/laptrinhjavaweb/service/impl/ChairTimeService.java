package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.ChairTimeConverter;
import com.laptrinhjavaweb.dto.ChairTimeDTO;
import com.laptrinhjavaweb.dto.ComboDTO;
import com.laptrinhjavaweb.dto.ComboTicketDTO;
import com.laptrinhjavaweb.entity.ChairEntity;
import com.laptrinhjavaweb.entity.ChairTimeEntity;
import com.laptrinhjavaweb.entity.ComboEntity;
import com.laptrinhjavaweb.entity.ComboTicketEntity;
import com.laptrinhjavaweb.entity.MovieTimeRoomEntity;
import com.laptrinhjavaweb.repository.ChairRepository;
import com.laptrinhjavaweb.repository.ChairTimeRepository;
import com.laptrinhjavaweb.repository.MovieTimeRoomRepository;
import com.laptrinhjavaweb.service.IChairTimeService;

@Service
public class ChairTimeService implements IChairTimeService {

	@Autowired
	private ChairTimeRepository chairtimeRepo;

	@Autowired
	private ChairTimeConverter chairtimeConverter;

	@Autowired
	private ChairRepository chairRepo;

	@Autowired
	private MovieTimeRoomRepository movietimeroomRepo;

	@Override
	@Transactional
	public int addChairTime(ChairTimeDTO chairTimeDTO) throws Exception {
	    ChairTimeEntity chairtimeEntity = chairtimeConverter.toEntity(chairTimeDTO);
	    ChairEntity chairEntity = chairRepo.findOne(chairTimeDTO.getChair().getIdCinemaChair());
	    chairtimeEntity.setIdChair(chairEntity);
	    MovieTimeRoomEntity movietimeroomEntity = movietimeroomRepo.findOne(chairTimeDTO.getMovieTimeRoom().getIdMovieTimeRoom());
	    chairtimeEntity.setIdMovieTimeRoom(movietimeroomEntity);
	    chairtimeEntity.setStatus(1);
	    chairtimeEntity = chairtimeRepo.save(chairtimeEntity);
	    ChairTimeDTO newChairTimeDTO = chairtimeConverter.toDTO(chairtimeEntity);
	    System.out.println(newChairTimeDTO.getIdChairTime());
	    return newChairTimeDTO.getIdChairTime(); // Trả về ID từ DTO mới
	}

	@Override
	@Transactional
	public List<Integer> addChairsTime(int[] idChairs, int idMovieTimeRoom) throws Exception {
		List<Integer> lst_id = new ArrayList<Integer>();
		for(int id : idChairs) {
			ChairTimeDTO chairTimeDTO = new ChairTimeDTO();
			chairTimeDTO.setChair(chairRepo.findOne(id));
			chairTimeDTO.setMovieTimeRoom(movietimeroomRepo.findOne(idMovieTimeRoom));
//			chairtimeRepo.save(chairtimeConverter.toEntity(chairTimeDTO));
			int newId = addChairTime(chairTimeDTO);
			lst_id.add(Integer.valueOf(newId));
		}
		return lst_id;
	}

	

	@Override
	public void deleteChairTime(int idchair, int idMovieTimeRoom) {
		chairtimeRepo.deleteTicket(idchair, idMovieTimeRoom);
		
	}
	

	@Override
	public ChairTimeDTO updateChairTime(int idChairTime) throws Exception {
		ChairTimeEntity chairtimeEntity = chairtimeRepo.findOne(idChairTime);
		chairtimeEntity.setStatus(1);
		return chairtimeConverter.toDTO(chairtimeRepo.save(chairtimeEntity)	);
	}

	@Override
	public void updateChairsTime(int[] idChairTimes) throws Exception {
		for(int id : idChairTimes) {
			updateChairTime(id);
		}
	}

	@Override
	public List<ChairTimeDTO> getchairtime() throws Exception {
		List<ChairTimeEntity> lstEntity = chairtimeRepo.findAll();
        List<ChairTimeDTO> lst = new ArrayList<>();
        for (ChairTimeEntity Entity : lstEntity) {
        	ChairTimeDTO dto = chairtimeConverter.toDTO(Entity);
            lst.add(dto);
        }
        return lst;
	}

	@Override
	public List<ChairTimeDTO> getchairtimebyID(int id) throws Exception {
		List<ChairTimeEntity> lstChairticket = chairtimeRepo.getChairTime(id);
        List<ChairTimeDTO> lst = new ArrayList<>();
        for (ChairTimeEntity combo: lstChairticket) {
        	ChairTimeDTO dto = chairtimeConverter.toDTO(combo);
            lst.add(dto);
        }
        return lst;
	}

}
