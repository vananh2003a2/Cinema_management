package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.ChairTicketDTO;
import com.laptrinhjavaweb.dto.ComboTicketDTO;
import com.laptrinhjavaweb.dto.MovieDTO;
import com.laptrinhjavaweb.dto.TicketDTO;

public interface ITicketService {
	void addTicket(TicketDTO ticketDTO);
    void addTicketToChair(ChairTicketDTO chairTicketDTO);
    void addTicketToCombo(ComboTicketDTO comboTicketDTO);
    int getLastInsertedTicketId();
    void xacnhanvethanhcong(TicketDTO ticketDTO);
    void xacnhanvethatbai(TicketDTO ticketDTO);
    void xacnhanhuyve(TicketDTO ticketDTO);
    void huyve(TicketDTO ticketDTO);
    void huygiaodich(TicketDTO ticketDTO);
    List<TicketDTO> getticket() throws Exception;
    TicketDTO getTicketForStatus(int id) throws Exception;
}
