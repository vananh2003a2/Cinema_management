package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.ComboTicketDTO;
import com.laptrinhjavaweb.dto.TicketDTO;

public interface IComBoTichketService {
	void deleteComboTicket(ComboTicketDTO comboTicketDTO);

	//lấy danh sách comboticket
	List<ComboTicketDTO> getcomboticket() throws Exception;
	List<ComboTicketDTO> getcomboticketByID(int id) throws Exception;
}
