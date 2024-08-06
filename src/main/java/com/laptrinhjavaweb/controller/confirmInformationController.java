package com.laptrinhjavaweb.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.laptrinhjavaweb.dto.ChairDTO;
import com.laptrinhjavaweb.dto.ChairTicketDTO;
import com.laptrinhjavaweb.dto.ChairTimeDTO;
import com.laptrinhjavaweb.dto.ComboDTO;
import com.laptrinhjavaweb.dto.ComboTicketDTO;
import com.laptrinhjavaweb.dto.MovieDTO;
import com.laptrinhjavaweb.dto.MovieTimeRoomDTO;
import com.laptrinhjavaweb.dto.RoomDTO;
import com.laptrinhjavaweb.dto.TicketDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.ComboEntity;
import com.laptrinhjavaweb.service.IChairService;
import com.laptrinhjavaweb.service.IChairTicketService;
import com.laptrinhjavaweb.service.IChairTimeService;
import com.laptrinhjavaweb.service.IComBoTichketService;
import com.laptrinhjavaweb.service.IComboService;
import com.laptrinhjavaweb.service.IMovieService;
import com.laptrinhjavaweb.service.IMovieTimeRoomService;
import com.laptrinhjavaweb.service.IRoomService;
import com.laptrinhjavaweb.service.ITicketService;
import com.laptrinhjavaweb.service.IUserService;

@Controller
public class confirmInformationController {
	@Autowired
	private ITicketService ticketService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoomService roomService;
	@Autowired
	private IMovieService movieService;
	@Autowired
	private IComboService comboService;
	@Autowired
	private IComBoTichketService comboticketService;
	@Autowired
	private IChairService chairService;
	@Autowired
	private IChairTicketService chairticketService;
	@Autowired
	private IMovieTimeRoomService movieTimeRoomService;
	@Autowired
	private IChairTimeService chairtimeService;

	@RequestMapping(value = "/huyve")
	public String huyve(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			String idTimeRoom = request.getParameter("idTimeRoom");
			String idChair = request.getParameter("idChairs");
			float comboPrice = Integer.parseInt(request.getParameter("comboPrice"));
			float chairPrice = Integer.parseInt(request.getParameter("chairSumPrice"));

			int idTicket = ticketService.getLastInsertedTicketId();

			float orderTotal = comboPrice + chairPrice;
			TicketDTO ticketDTO = new TicketDTO();
			ticketDTO.setId_Ticket(idTicket);
			ticketDTO.setTotalAmount(orderTotal);
			ticketService.huygiaodich(ticketDTO);
			redirectAttributes.addAttribute("message", "Bạn đã xác nhận hủy vé thành công!");
			return "redirect:/index";
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page"; // Trả về trang lỗi nếu có lỗi xảy ra
		}
	}

	@RequestMapping(value = "/xacnhanthongtin", method = RequestMethod.POST)
	public String xacnhanthongtin(HttpServletRequest request, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {

		// Lấy các tham số từ request
		String idUser = request.getParameter("idUser");
		String idMovie = request.getParameter("idMovie");
		String idTimeRoom = request.getParameter("idTimeRoom");
		String idRoom = request.getParameter("idRoom");
		String idChairs = request.getParameter("selectedChairs");
		String comboIds = request.getParameter("idCombo");
		String comboName = request.getParameter("comboName");
		String comboQuantityParam = request.getParameter("comboQuantity");
		String comboPriceParam = request.getParameter("comboPrice");
		int ticketId = ticketService.getLastInsertedTicketId();
		TicketDTO ticketDTO = ticketService.getTicketForStatus(ticketId);
		if (idChairs != null && !idChairs.isEmpty() && comboName != null && !comboName.isEmpty()
				&& request.getParameter("comboQuantity") != null && !request.getParameter("comboQuantity").isEmpty()
				&& request.getParameter("comboPrice") != null && !request.getParameter("comboPrice").isEmpty()) {
			try {
				int comboQuantity = Integer.parseInt(comboQuantityParam);
				float comboPrice = Float.parseFloat(comboPriceParam);
				ticketId = ticketService.getLastInsertedTicketId();

				// Lấy thông tin khách hàng
				UserDTO userDTO = userService.getUserById(Integer.parseInt(idUser));
				RoomDTO roomDTO = roomService.getRoomById(Integer.parseInt(idRoom));
				MovieDTO movieDTO = movieService.getMoviesById(Integer.parseInt(idMovie));
				MovieTimeRoomDTO movieTimeRoomDTO = movieTimeRoomService.getTimeRoom(Integer.parseInt(idTimeRoom));
				ComboTicketDTO comboTicketDTO = new ComboTicketDTO();
				comboTicketDTO.setIdTicket(ticketId);
				ComboDTO comboDTO = new ComboDTO();
				String[] combo = comboIds.split(",");
				for (String c : combo) {
					comboDTO = comboService.getCombobyid(Integer.parseInt(c));

					comboTicketDTO.setIdCombo(comboDTO.getIdcombo());
					comboTicketDTO.setSumPrice(comboDTO.getComboprice() * comboQuantity);
					comboTicketDTO.setQuantity(comboQuantity);
					ticketService.addTicketToCombo(comboTicketDTO);
				}
				// Tách các id ghế đã chọn và lấy thông tin chi tiết của từng ghế
				List<ChairDTO> selectedChairs = new ArrayList<>();
				String[] chairIds = idChairs.split(",");
				int chairSumPrice = 0;
				ChairDTO chairDTO = new ChairDTO();
				int dem = 0;
				for (String chairId : chairIds) {
					int id = Integer.parseInt(chairId.trim());
					chairDTO = chairService.getChairbyid(id);
					dem++;
					chairSumPrice += chairDTO.getIdChairType().getPrice();
					selectedChairs.add(chairDTO);
				}
				redirectAttributes.addAttribute("idTicket", ticketId);
				redirectAttributes.addAttribute("idChairs", idChairs);
				redirectAttributes.addAttribute("idUser", userDTO.getIdUser());
				redirectAttributes.addAttribute("idTimeRoom", idTimeRoom);
				
				redirectAttributes.addAttribute("idMovie", idMovie);
				redirectAttributes.addAttribute("idRoom", idRoom);
				
				redirectAttributes.addAttribute("hoten", userDTO.getFullName());
				redirectAttributes.addAttribute("email", userDTO.getEmail());
				redirectAttributes.addAttribute("sdt", userDTO.getPhone());
				redirectAttributes.addAttribute("phongchieu", roomDTO.getRoomCode());
				redirectAttributes.addAttribute("tenphim", movieDTO.getMovieName());
				redirectAttributes.addAttribute("anh", movieDTO.getMainImage());
				redirectAttributes.addAttribute("ngaychieu", ticketDTO.getBookingTime());
				redirectAttributes.addAttribute("suatchieu", movieTimeRoomDTO.getBeginTime());
				redirectAttributes.addAttribute("chairQuantity", dem);
				redirectAttributes.addAttribute("chairSumPrice", String.valueOf(chairSumPrice));
				redirectAttributes.addAttribute("comboQuantity", request.getParameter("comboQuantity"));
				redirectAttributes.addAttribute("comboName", comboName);
				redirectAttributes.addAttribute("comboPrice", request.getParameter("comboPrice"));

				return "redirect:/hienthixacnhanthongtin";
			} catch (NumberFormatException e) {
				// Xử lý ngoại lệ NumberFormatException
				e.printStackTrace();
				return "error-page";
			}

		} else {
			// Lấy thông tin khách hàng
			UserDTO userDTO = userService.getUserById(Integer.parseInt(idUser));
			RoomDTO roomDTO = roomService.getRoomById(Integer.parseInt(idRoom));
			MovieDTO movieDTO = movieService.getMoviesById(Integer.parseInt(idMovie));
			MovieTimeRoomDTO movieTimeRoomDTO = movieTimeRoomService.getTimeRoom(Integer.parseInt(idTimeRoom));
			// Tách các id ghế đã chọn và lấy thông tin chi tiết của từng ghế
			List<ChairDTO> selectedChairs = new ArrayList<>();
			String[] chairIds = idChairs.split(",");
			int chairSumPrice = 0;
			ChairDTO chairDTO = new ChairDTO();
			int dem = 0;
			for (String chairId : chairIds) {
				int id = Integer.parseInt(chairId.trim());
				chairDTO = chairService.getChairbyid(id);
				dem++;
				chairSumPrice += chairDTO.getIdChairType().getPrice();
				selectedChairs.add(chairDTO);
			}
			
			redirectAttributes.addAttribute("idMovie", idMovie);
			redirectAttributes.addAttribute("idRoom", idRoom);
			redirectAttributes.addAttribute("anh", movieDTO.getMainImage());
			redirectAttributes.addAttribute("idUser", userDTO.getIdUser());
			redirectAttributes.addAttribute("idTicket", ticketId);
			redirectAttributes.addAttribute("idChairs", idChairs);
			redirectAttributes.addAttribute("idTimeRoom", idTimeRoom);
			redirectAttributes.addAttribute("hoten", userDTO.getFullName());
			redirectAttributes.addAttribute("email", userDTO.getEmail());
			redirectAttributes.addAttribute("sdt", userDTO.getPhone());
			redirectAttributes.addAttribute("phongchieu", roomDTO.getRoomCode());
			redirectAttributes.addAttribute("tenphim", movieDTO.getMovieName());
			redirectAttributes.addAttribute("ngaychieu", movieDTO.getMovieDate());
			redirectAttributes.addAttribute("suatchieu", movieTimeRoomDTO.getBeginTime());
			redirectAttributes.addAttribute("chairQuantity", dem);
			redirectAttributes.addAttribute("chairSumPrice", chairSumPrice);

			// Redirect đến trang hienthixacnhanthongtin
			return "redirect:/hienthixacnhanthongtin";
		}

	}

	@RequestMapping(value = "/hienthixacnhanthongtin", method = RequestMethod.GET)
	public ModelAndView hienthixacnhan(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();

		// Lấy các tham số từ request
		String idUser = request.getParameter("idUser");
		String hoten = request.getParameter("hoten");
		String email = request.getParameter("email");
		String sdt = request.getParameter("sdt");
		String idMovie = request.getParameter("idMovie");
		String tenphim = request.getParameter("tenphim");
		String ngaychieu = request.getParameter("ngaychieu");
		String suatchieu = request.getParameter("suatchieu");
		String idTimeRoom = request.getParameter("idTimeRoom");
		String anh = request.getParameter("anh");

		String idRoom = request.getParameter("idRoom");
		String phongchieu = request.getParameter("phongchieu");
		String chairQuantity = request.getParameter("chairQuantity");
		String chairSumPrice = request.getParameter("chairSumPrice");
		String comboIds = request.getParameter("idCombo");
		String comboName = request.getParameter("comboName");
		String comboQuantity = request.getParameter("comboQuantity");
		String comboPrice = request.getParameter("comboPrice");
		String idChairs = request.getParameter("idChairs");
		String idTicket = request.getParameter("idTicket");
		TicketDTO ticketDTO = ticketService.getTicketForStatus(Integer.parseInt(idTicket));
		String[] chairIds = idChairs.split(",");
		List<ChairDTO> selectedChairs = new ArrayList<>();
		// Lặp qua mỗi ID ghế và lấy thông tin chi tiết từ ID đó
		for (String chairId : chairIds) {
			int id = Integer.parseInt(chairId.trim());
			ChairDTO chairDTO = chairService.getChairbyid(id);
			selectedChairs.add(chairDTO);
		}
		// Gửi danh sách các ghế đã chọn đến view
		mav.addObject("idTicket", idTicket);
		mav.addObject("idChairs", idChairs);
		mav.addObject("selectedChairs", selectedChairs);
		mav.addObject("idUser", idUser);
		mav.addObject("hoten", hoten);
		mav.addObject("email", email);
		mav.addObject("sdt", sdt);
		mav.addObject("idRoom", idRoom);
		mav.addObject("phongchieu", phongchieu);
		mav.addObject("idMovie", idMovie);
		mav.addObject("tenphim", tenphim);
		mav.addObject("ngaychieu", ticketDTO.getBookingTime());
		mav.addObject("suatchieu", suatchieu);
		mav.addObject("idTimeRoom", idTimeRoom);
		mav.addObject("anh", anh);
		// Gửi danh sách các ghế đã chọn đến view
		mav.addObject("chairQuantity", chairQuantity);
		mav.addObject("chairSumPrice", chairSumPrice);

		session.setAttribute("idTicket", idTicket);
		if (idChairs != null && !idChairs.isEmpty() && comboName != null && !comboName.isEmpty()
				&& request.getParameter("comboQuantity") != null && !request.getParameter("comboQuantity").isEmpty()
				&& request.getParameter("comboPrice") != null && !request.getParameter("comboPrice").isEmpty()) {
			mav.addObject("idCombo", comboIds);
			mav.addObject("comboName", comboName);
			mav.addObject("comboQuantity", comboQuantity);
			mav.addObject("comboPrice", comboPrice);
		} else {
			mav.addObject("idCombo", " ");
			mav.addObject("comboName", "Không có combo");
			mav.addObject("comboQuantity", 0);
			mav.addObject("comboPrice", 0);
		}

		mav.setViewName("customer/confirmInformation");

		return mav;
	}

}
