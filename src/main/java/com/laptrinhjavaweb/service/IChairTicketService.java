package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.ChairTicketDTO;
import com.laptrinhjavaweb.dto.ComboDTO;

public interface IChairTicketService {
	void addChairTicket(ChairTicketDTO chairTicketDTO);
	void deleteChairTicket(ChairTicketDTO chairTicketDTO);

	List<ChairTicketDTO> getchairtiket() throws Exception;
	List<ChairTicketDTO> getchairtiketID(int id) throws Exception;
}
