package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.TicketConverter;
import com.laptrinhjavaweb.dto.ChairTicketDTO;
import com.laptrinhjavaweb.dto.ComboTicketDTO;
import com.laptrinhjavaweb.dto.TicketDTO;
import com.laptrinhjavaweb.entity.TicketEntity;
import com.laptrinhjavaweb.repository.TicketRepository;
import com.laptrinhjavaweb.service.ITicketService;
@Service
public class TicketServive implements ITicketService{
	@Autowired
    private TicketRepository ticketRepository;
	@Autowired
    private TicketConverter cv;

	@Override
	public void addTicket(TicketDTO ticketDTO) {
		ticketRepository.addTicket(ticketDTO.getTicketCode(),ticketDTO.getIdMovie(),ticketDTO.getIdUser());
		
	}

	@Override
	public void addTicketToChair(ChairTicketDTO chairTicketDTO) {
		
		
		ticketRepository.addTicketToChair(chairTicketDTO.getIdChairTime(), chairTicketDTO.getIdTicket());
	}

	@Override
	public void addTicketToCombo(ComboTicketDTO comboTicketDTO) {
		ticketRepository.addTicketToCombo(comboTicketDTO.getIdCombo(), comboTicketDTO.getIdTicket(), comboTicketDTO.getQuantity(), comboTicketDTO.getSumPrice());
		
	}

	@Override
	public int getLastInsertedTicketId() {
		
		return ticketRepository.findLastInsertedTicket();
	}

	@Override
	public void xacnhanvethanhcong(TicketDTO ticketDTO) {
		ticketRepository.updateTicketSuccess( ticketDTO.getTicketCode(),ticketDTO.getTotalAmount(), ticketDTO.getId_Ticket());
		
	}
	@Override
	public void xacnhanvethatbai(TicketDTO ticketDTO) {
		ticketRepository.updateTicketFail(ticketDTO.getTicketCode(),ticketDTO.getTotalAmount(), ticketDTO.getId_Ticket());
		
	}
	@Override
	public void xacnhanhuyve(TicketDTO ticketDTO) {
		ticketRepository.updateTicketExit(ticketDTO.getTotalAmount(), ticketDTO.getId_Ticket());
	}
	@Override
	public void huyve(TicketDTO ticketDTO) {
		ticketRepository.deleteTicket(ticketDTO.getId_Ticket());
		
	}

	@Override
	public void huygiaodich(TicketDTO ticketDTO) {
		ticketRepository.updateTransactionfailed(ticketDTO.getTotalAmount(), ticketDTO.getId_Ticket());
		
	}

	@Override
	public List<TicketDTO> getticket() throws Exception {
		List<TicketEntity> lstEntity = ticketRepository.findAll();
        List<TicketDTO> lst = new ArrayList<>();
        for (TicketEntity Entity : lstEntity) {
        	TicketDTO dto = cv.toDTO(Entity);
            lst.add(dto);
        }
        return lst;
	}

	@Override
	public TicketDTO getTicketForStatus(int id) throws Exception {
		// TODO Auto-generated method stub
		TicketDTO ticketDTO = new TicketDTO();
		ticketDTO = cv.toDTO(ticketRepository.findTicket(id));
		return  ticketDTO;
	}

	//Bắt đầu giữ chỗ

		@Scheduled(fixedDelay = 5000)
		private void isExpired() {
			// Kiểm tra xem thời gian đặt vé cách thời gian hiện tại quá 5 phút hay không
			// Nếu cách quá 5 phút, đặt vé được coi là hết hạn
			long currentTimeMillis = System.currentTimeMillis();
			List<TicketEntity> lst= ticketRepository.findAll();
			for (TicketEntity ticketEntity : lst) {
				long bookingTimeMillis = ticketEntity.getBookingTime().getTime();
				System.out.println(ticketEntity.getId_Ticket());
				if((currentTimeMillis - bookingTimeMillis) >= 5 * 60 * 1000){
//					System.out.println((currentTimeMillis - bookingTimeMillis) >= 5 * 60 * 1000);
//					System.out.println(ticketEntity.getId_Ticket());
//					System.out.println(ticketEntity.getStatus());
					if (ticketEntity.getStatus()!=1) {
						
						//cập nhật lại trạng thái ghế
						ticketRepository.updateChairtimeStatus(ticketEntity.getId_Ticket());
					}
				}; // 5 phút tính bằng milliseconds
			}
		}
}
