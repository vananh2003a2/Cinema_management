package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.AdminTicketDTO;

public interface IAdminTicketService {
	List<AdminTicketDTO> getAllAdminTicket()  throws Exception;
	List<AdminTicketDTO> getTicketByKey(String key) throws Exception;
	List<AdminTicketDTO> getTicketByStatus(int status) throws Exception;
	List<AdminTicketDTO> getTicketByKeyAndStatus(int status, String key) throws Exception;
}
