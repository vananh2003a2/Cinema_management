package com.laptrinhjavaweb.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.laptrinhjavaweb.config.VNPayConfig;
import com.laptrinhjavaweb.dto.ChairDTO;
import com.laptrinhjavaweb.dto.ChairTicketDTO;
import com.laptrinhjavaweb.dto.ChairTimeDTO;
import com.laptrinhjavaweb.dto.ChairTypeDTO;
import com.laptrinhjavaweb.dto.ComboDTO;
import com.laptrinhjavaweb.dto.ComboTicketDTO;
import com.laptrinhjavaweb.dto.MovieDTO;
import com.laptrinhjavaweb.dto.MovieTimeRoomDTO;
import com.laptrinhjavaweb.dto.PaymentDTO;
import com.laptrinhjavaweb.dto.RoomDTO;
import com.laptrinhjavaweb.dto.TicketDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.repository.ChairTypeRepository;
import com.laptrinhjavaweb.service.ChairTypeService;
import com.laptrinhjavaweb.service.IChairService;
import com.laptrinhjavaweb.service.IChairTicketService;
import com.laptrinhjavaweb.service.IChairTimeService;
import com.laptrinhjavaweb.service.IComBoTichketService;
import com.laptrinhjavaweb.service.IComboService;
import com.laptrinhjavaweb.service.IMovieService;
import com.laptrinhjavaweb.service.ITicketService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.service.impl.MovieTimeRoomService;
import com.laptrinhjavaweb.service.impl.RoomService;
import com.laptrinhjavaweb.service.impl.VNPayService;
import com.opensymphony.module.sitemesh.Config;

@Controller
public class PaymentController {

	@Autowired
	private ITicketService ticketService;

	@Autowired
	private VNPayService vnPayService;
	@Autowired
	private IMovieService movieService;
	@Autowired
	private IChairTicketService chairticketService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IChairService chairService;
	@Autowired
	private IComBoTichketService comboticketService;
	@Autowired
	private IChairTimeService chairtimeService;
	@Autowired
	private IComboService comboService;
	@Autowired
	private ChairTypeService chairTypeService;
	@Autowired
	private MovieTimeRoomService movieTimeRoomService;
	@Autowired
	private RoomService roomService;

	@RequestMapping(value = "/submitOrder", method = RequestMethod.GET)
	public ModelAndView submitOrder(HttpSession session, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView("customer/payment");

		int comboPrice = Integer.parseInt(request.getParameter("comboPrice"));
		int chairPrice = Integer.parseInt(request.getParameter("chairSumPrice"));
		String idTimeRoom = request.getParameter("idTimeRoom");
		String idChair = request.getParameter("idChairs");
		String idTicket = request.getParameter("idTicket");
		int orderTotal = comboPrice + chairPrice;
		String orderInfo = idTicket;

		String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);

		mav.addObject("vnpayUrl", vnpayUrl);
		mav.addObject("orderTotal", orderTotal);
		mav.addObject("orderInfo", orderInfo);
		mav.addObject("idTicket", idTicket);
		mav.addObject("idTimeRoom", idTimeRoom);
		mav.addObject("idChairs", idChair);
		return mav;
	}

	@RequestMapping(value = "/vnpay-payment", method = RequestMethod.GET)
	public String GetMapping(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
		try {
			int paymentStatus = vnPayService.orderReturn(request);
			String ticketCode = request.getParameter("vnp_TxnRef");
			String orderInfo = request.getParameter("vnp_OrderInfo");
			String paymentTime = request.getParameter("vnp_PayDate");
			String transactionId = request.getParameter("vnp_TransactionNo");
			String totalPrice = request.getParameter("vnp_Amount");
	

		

			redirectAttributes.addAttribute("ticketCode", ticketCode);
			redirectAttributes.addAttribute("orderId", orderInfo);
			redirectAttributes.addAttribute("totalPrice", totalPrice);
			redirectAttributes.addAttribute("paymentTime", paymentTime);
			redirectAttributes.addAttribute("transactionId", transactionId);
			TicketDTO ticketDTO = new TicketDTO();
			ticketDTO.setId_Ticket(Integer.parseInt(orderInfo));
			ticketDTO.setTotalAmount(Float.parseFloat(totalPrice) / 100);
			ticketDTO.setTicketCode(ticketCode);

			if (paymentStatus == 1) {
				redirectAttributes.addAttribute("message", "Thanh toán thành công");
				List<Integer> isdChairTime = (List<Integer>) session.getAttribute("idsChairTime");
				int ids[] = new int[isdChairTime.size()];
				for (int i = 0; i < isdChairTime.size(); i++) {
					ids[i] = isdChairTime.get(i).intValue();
				}
				chairtimeService.updateChairsTime(ids);
				ticketService.xacnhanvethanhcong(ticketDTO);
				return "redirect:/orderticket";
			} else {
				redirectAttributes.addAttribute("message", "Thanh toán thất bại");

				ticketService.xacnhanvethatbai(ticketDTO);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/index";
	}

	@RequestMapping(value = "/cancelTicket", method = RequestMethod.GET)
	public String cancelTicket(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String orderInfo = request.getParameter("orderInfo");
		String orderTotal = request.getParameter("orderTotal");

		TicketDTO dto = new TicketDTO();
		dto.setId_Ticket(Integer.parseInt(orderInfo));
		dto.setTotalAmount(Float.parseFloat(orderTotal));

		ticketService.xacnhanhuyve(dto);
		redirectAttributes.addAttribute("message", "Bạn đã xác nhận hủy vé thành công!");

		return "redirect:/index";
	}

	@RequestMapping(value = "/orderticket", method = RequestMethod.GET)
	public ModelAndView Pageorderticket(HttpSession session, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("customer/orderticket");
		String idChairs = request.getParameter("idChairs");
		String message = request.getParameter("message");
		String idTimeRoom = request.getParameter("idTimeRoom");
		String idTicket = request.getParameter("orderId");
		String totalPrice = request.getParameter("totalPrice");
		TicketDTO ticketDTO = ticketService.getTicketForStatus(Integer.parseInt(idTicket));
		mav.addObject("ticketCode", ticketDTO.getTicketCode());
		mav.addObject("ngaychieu", ticketDTO.getBookingTime());
		
		UserDTO userDTO = userService.getUserById(ticketDTO.getIdUser());
		mav.addObject("hoten", userDTO.getFullName());
		mav.addObject("email", userDTO.getEmail());
		mav.addObject("sdt", userDTO.getPhone());
		
		MovieDTO movieDTO = movieService.getMoviesById(ticketDTO.getIdMovie());
		mav.addObject("tenphim", movieDTO.getMovieName());
		
		
		List<ComboTicketDTO> lstcomboTicket = comboticketService.getcomboticketByID(ticketDTO.getId_Ticket());
		float ComboPrice = 0; 
		int comboQuantity = 0;
		ArrayList<String> ComboName = new ArrayList<>();
		List<ComboDTO> lstcombo = new ArrayList<ComboDTO>();
		for (ComboTicketDTO comboTicketDTO : lstcomboTicket) {
			lstcombo = comboService.getCombobyid2(comboTicketDTO.getIdCombo());
			for (ComboDTO comboDTO2 : lstcombo) {
				ComboName.add(comboDTO2.getComboname()) ;
				ComboPrice+=comboDTO2.getComboprice();
			}
			comboQuantity++;
		}
		
		List<ChairTicketDTO> lstChairTicketDTO = chairticketService.getchairtiketID(ticketDTO.getId_Ticket());
		List<ChairTimeDTO> lstChairTimeDTO = new ArrayList<ChairTimeDTO>();
		List<ChairDTO> lstChairDTO = new ArrayList<ChairDTO>();
		List<ChairTypeDTO> lstChairTypeDTO = new ArrayList<ChairTypeDTO>();
		ArrayList<String> chairCode = new ArrayList<>();
		float ChairPrice = 0; 
		int ChairQuantity = 0;
		int idMovieTimeRoom = 0;
		for (ChairTicketDTO chairTicketDTO : lstChairTicketDTO) {
			lstChairTimeDTO = chairtimeService.getchairtimebyID(chairTicketDTO.getIdChairTime());
			idMovieTimeRoom = lstChairTimeDTO.get(0).getMovieTimeRoom().getIdMovieTimeRoom();
			for (ChairTimeDTO chairTimeDTO : lstChairTimeDTO) {
				lstChairDTO = chairService.getchairById(chairTimeDTO.getChair().getIdCinemaChair());
				for (ChairDTO chairDTO : lstChairDTO) {
					chairCode.add(chairDTO.getChairCode());
					lstChairTypeDTO = chairTypeService.getchairtypeById(chairDTO.getIdChairType().getIdChairType());
					for (ChairTypeDTO chairTypeDTO : lstChairTypeDTO) {
						ChairPrice+=chairTypeDTO.getPrice();
					}
				}
				
			}
			ChairQuantity++;
		}
		MovieTimeRoomDTO movieTimeRoomDTO = movieTimeRoomService.getTimeRoom(idMovieTimeRoom);
		int idRoom = movieTimeRoomDTO.getIdRoom();
		RoomDTO roomDTO = roomService.getRoomById(idRoom);
		// Gửi danh sách các ghế đã chọn đến view
		mav.addObject("message", message);
		mav.addObject("idTicket", idTicket);
		mav.addObject("comboPrice", ComboPrice);
		mav.addObject("comboQuantity", comboQuantity);
		mav.addObject("comboName", ComboName);
		mav.addObject("chairQuantity", ChairQuantity);
		mav.addObject("chairCode",chairCode);
		mav.addObject("chairPrice",ChairPrice);
		mav.addObject("phongchieu",roomDTO.getRoomCode());
		mav.addObject("suatchieu",movieTimeRoomDTO.getBeginTime());
		mav.addObject("totalPrice", Long.parseLong(totalPrice)/100);
		return mav;
	}
}
