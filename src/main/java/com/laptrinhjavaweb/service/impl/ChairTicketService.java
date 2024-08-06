package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.ChairTicketConverter;
import com.laptrinhjavaweb.dto.ChairTicketDTO;
import com.laptrinhjavaweb.dto.ComboDTO;
import com.laptrinhjavaweb.dto.ComboTicketDTO;
import com.laptrinhjavaweb.entity.ChairTicketEntity;
import com.laptrinhjavaweb.entity.ComboEntity;
import com.laptrinhjavaweb.entity.ComboTicketEntity;
import com.laptrinhjavaweb.repository.ChairTicketRepository;
import com.laptrinhjavaweb.service.IChairTicketService;
@Service
public class ChairTicketService implements IChairTicketService{

	@Autowired
	private ChairTicketRepository chairTicketRepository;
	@Autowired
	private ChairTicketConverter cv;
	
	@Override
	public void addChairTicket(ChairTicketDTO chairTicketDTO) {
		chairTicketRepository.addTicket(chairTicketDTO.getIdChairTime(), chairTicketDTO.getIdTicket());
		
	}
	@Override
	public void deleteChairTicket(ChairTicketDTO chairTicketDTO) {
	    chairTicketRepository.deleteTicket(chairTicketDTO.getIdTicket());
	}
	@Override
	public List<ChairTicketDTO> getchairtiket() throws Exception {
		List<ChairTicketEntity> lstEntity = chairTicketRepository.findAll();
        List<ChairTicketDTO> lst = new ArrayList<>();
        for (ChairTicketEntity Entity : lstEntity) {
        	ChairTicketDTO dto = cv.toDTO(Entity);
            lst.add(dto);
        }
        return lst;
	}
	@Override
	public List<ChairTicketDTO> getchairtiketID(int id) throws Exception {
		List<ChairTicketEntity> lstComboticket = chairTicketRepository.getChairTicket(id);
        List<ChairTicketDTO> lst = new ArrayList<>();
        for (ChairTicketEntity combo: lstComboticket) {
        	ChairTicketDTO dto = cv.toDTO(combo);
            lst.add(dto);
        }
        return lst;
	}
	
}
