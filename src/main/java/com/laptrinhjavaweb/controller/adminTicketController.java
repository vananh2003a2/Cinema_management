package com.laptrinhjavaweb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.AdminTicketDTO;
import com.laptrinhjavaweb.dto.ChairDTO;
import com.laptrinhjavaweb.dto.ChairTicketDTO;
import com.laptrinhjavaweb.dto.ChairTimeDTO;
import com.laptrinhjavaweb.dto.ComboDTO;
import com.laptrinhjavaweb.dto.ComboTicketDTO;
import com.laptrinhjavaweb.dto.TicketDTO;
import com.laptrinhjavaweb.service.IAdminTicketService;
import com.laptrinhjavaweb.service.IChairService;
import com.laptrinhjavaweb.service.IChairTicketService;
import com.laptrinhjavaweb.service.IChairTimeService;
import com.laptrinhjavaweb.service.IComBoTichketService;
import com.laptrinhjavaweb.service.IComboService;
import com.laptrinhjavaweb.service.IMovieTimeRoomService;
import com.laptrinhjavaweb.service.ITicketService;
import com.laptrinhjavaweb.service.impl.ComboService;

@Controller
public class adminTicketController {
	@Autowired
	private IAdminTicketService adminticketservice;
	
	@Autowired 
	private IComboService comboservice;
	
	@Autowired 
	private IComBoTichketService comboticketservice;

	@RequestMapping(value = "/admin/ticket", method = RequestMethod.GET)
	public ModelAndView adminTicketPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("admin/ticket");

		try {
			String key = request.getParameter("txtsearch");
			String status = request.getParameter("status");
			// Lưu trạng thái vào request để sử dụng lại
			request.setAttribute("searchStatus", status);

			mav.addObject("key", key);
			mav.addObject("status", status);

			// Tính toán trang và kích thước trang
			int page = 0; // Trang mặc định
			int size = 5; // Số lượng phần tử trên mỗi trang
			String pageStr = request.getParameter("page");
			if (pageStr != null && !pageStr.isEmpty()) {
				page = Integer.parseInt(pageStr);
			}
			mav.addObject("currentPage", page);

			List<AdminTicketDTO> listticket = new ArrayList<>();
			List<AdminTicketDTO> uniqueTickets = new ArrayList<>(); // uniqueTickets lưu danh sách vé duy nhất
			List<AdminTicketDTO> duplicateTickets = new ArrayList<>(); // duplicateTickets lưu danh sách vé trùng lặp để
																		// xử lý lấy ra ds ghế
			List<AdminTicketDTO> uniqueChair = new ArrayList<>(); // uniqueChair lưu danh sách vé không bị trùng lặp ghế
			List<ComboTicketDTO> comboticket = comboticketservice.getcomboticket();
			List<ComboDTO> combo = comboservice.getcombo();

			// Xử lý lọc và tìm kiếm vé
			if ((key == null || key.isEmpty()) && (status == null || status.isEmpty())) {
				listticket = adminticketservice.getAllAdminTicket(); // nếu điều kiện tìm kiếm không có thì hiển thị tất
																		// cả vé
			} else if (!key.isEmpty() && !status.equals("")) {
				int parseStatus = Integer.parseInt(status);
				listticket = adminticketservice.getTicketByKeyAndStatus(parseStatus, key); // Tìm vé theo cả key và
																							// status
			} else if (key.isEmpty() && !status.equals("")) {
				int parseStatus = Integer.parseInt(status);
				listticket = adminticketservice.getTicketByStatus(parseStatus); // Tìm vé theo status
			} else if (!key.isEmpty() && status.equals("")) {
				listticket = adminticketservice.getTicketByKey(key); // Tìm vé theo key
			}

			// Xử lý danh sách vé để chỉ hiển thị các vé duy nhất
			if (!listticket.isEmpty()) {
				uniqueTickets.add(listticket.get(0));
				int ticketSize = listticket.size();
				int previousId = listticket.get(0).getId_Ticket();
				for (int i = 0; i < ticketSize; i++) {
					int currentId = listticket.get(i).getId_Ticket();
					if (currentId != previousId) {
						uniqueTickets.add(listticket.get(i));
						previousId = currentId;
					} else if (currentId == previousId) {
						duplicateTickets.add(listticket.get(i));
					}
				}
			}

//			for (AdminTicketDTO item : uniqueTickets) {
//				System.out.println("id TicketOfDup:" + item.getId_Ticket());
//			}
			
			for (AdminTicketDTO item1 : uniqueTickets) {
				for (AdminTicketDTO item2 : listticket) {
					if (item1.getId_Ticket() == item2.getId_Ticket()) {
						boolean check = true;
						if (uniqueChair.size() != 0) {
							for (AdminTicketDTO item3 : uniqueChair) {
								if( item2.getChairCode()!=null && item3.getChairCode()!=null) {
									if (item3.getChairCode().equals(item2.getChairCode()) && item3.getId_Ticket()==item2.getId_Ticket() ) {
										check = false;
										break;
									}
								}
								
							}
						}
						if (check) {
							uniqueChair.add(item2);
//							System.out.println(item2.toString());
						}
					}
				}
			}
			mav.addObject("uniqueChair", uniqueChair);

			// Tính chỉ mục bắt đầu và kết thúc cho trang hiện tại
			int start = page * size;
			int end = Math.min(start + size, uniqueTickets.size());
			List<AdminTicketDTO> paginatedTickets = uniqueTickets.subList(start, end);
//			for (AdminTicketDTO item : paginatedTickets) {
//				System.out.println("id TicketMaster:" + item.getId_Ticket());
//			}

			mav.addObject("listticket", paginatedTickets);
			mav.addObject("totalPages", (int) Math.ceil((double) uniqueTickets.size() / size));
			mav.addObject("totalrow", (int) Math.ceil((double) uniqueTickets.size()));
			mav.addObject("currentPage", page);
			mav.addObject("comboticket", comboticket);
			mav.addObject("combo", combo);

			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("admin/ticket");
		}
	}
}
