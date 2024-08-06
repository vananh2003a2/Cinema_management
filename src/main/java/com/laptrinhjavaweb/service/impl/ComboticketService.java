package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.ComboTicketConverter;
import com.laptrinhjavaweb.dto.ComboDTO;
import com.laptrinhjavaweb.dto.ComboTicketDTO;
import com.laptrinhjavaweb.entity.ComboEntity;
import com.laptrinhjavaweb.entity.ComboTicketEntity;
import com.laptrinhjavaweb.repository.ComboTicketRepository;
import com.laptrinhjavaweb.service.IComBoTichketService;
@Service
public class ComboticketService implements IComBoTichketService{
	@Autowired
	private ComboTicketRepository repo;
	
	@Autowired
	private ComboTicketConverter cv;
	
	@Override
	public void deleteComboTicket(ComboTicketDTO comboTicketDTO) {
		
		repo.deleteTicket(comboTicketDTO.getIdTicket());
	}

	@Override
	public List<ComboTicketDTO> getcomboticket() throws Exception {
		List<ComboTicketEntity> lstComboticket = repo.findAll();
        List<ComboTicketDTO> lst = new ArrayList<>();
        for (ComboTicketEntity combo: lstComboticket) {
        	ComboTicketDTO dto = cv.toDTO(combo);
            lst.add(dto);
        }
        return lst;
	}

	@Override
	public List<ComboTicketDTO> getcomboticketByID(int id) throws Exception {
		List<ComboTicketEntity> lstComboticket = repo.getComboTicket(id);
        List<ComboTicketDTO> lst = new ArrayList<>();
        for (ComboTicketEntity combo: lstComboticket) {
        	ComboTicketDTO dto = cv.toDTO(combo);
            lst.add(dto);
        }
        return lst;
	}

}
