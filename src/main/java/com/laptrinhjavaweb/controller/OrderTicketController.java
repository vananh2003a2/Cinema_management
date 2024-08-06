package com.laptrinhjavaweb.controller;

import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.ChairDTO;
import com.laptrinhjavaweb.dto.ChairTicketDTO;
import com.laptrinhjavaweb.dto.ComboDTO;
import com.laptrinhjavaweb.dto.MovieDTO;
import com.laptrinhjavaweb.dto.MovieTimeRoomDTO;
import com.laptrinhjavaweb.dto.MovieTimeRoomOfChairDTO;
import com.laptrinhjavaweb.dto.MyUser;
import com.laptrinhjavaweb.dto.RoomDTO;
import com.laptrinhjavaweb.dto.TicketDTO;
import com.laptrinhjavaweb.service.IChairService;
import com.laptrinhjavaweb.service.IChairTicketService;
import com.laptrinhjavaweb.service.IChairTimeService;
import com.laptrinhjavaweb.service.IComboService;
import com.laptrinhjavaweb.service.IMovieService;
import com.laptrinhjavaweb.service.IMovieTimeRoomService;
import com.laptrinhjavaweb.service.ITicketService;
import com.laptrinhjavaweb.utils.SecurityUtils;

@Controller(value = "orderTicketController")
public class OrderTicketController {

	@Autowired
	private IMovieService movieService;
	@Autowired
	private IComboService comboService;

	@Autowired
	private IMovieTimeRoomService movietimeroomService;
	@Autowired
	private ITicketService ticketService;

	@Autowired
	private IChairService chairService;

	@Autowired
	private IChairTimeService chairtimeService;
	@Autowired
	private IChairTicketService chairticketService;


	@GetMapping(value = "/pickchair")
	public ModelAndView PickChairs(@RequestParam(value = "idMovieTimeRoom", required = false) Integer idMovieTimeRoom,
			@RequestParam(value = "idMovie", required = false) Integer idMovie,
			@RequestParam(value = "idRoom", required = false) Integer idRoom,
			MovieDTO movieDto, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("customer/pickchair");
		int idUser = 0;
		String fullName = "";
		try {
			if (idMovieTimeRoom != null) {
				if (SecurityUtils.getPrincipal()!=null) {
					idUser = SecurityUtils.getPrincipal().getIdUser();
					fullName = SecurityUtils.getPrincipal().getFullName();
				}
				MovieTimeRoomOfChairDTO mtr = new MovieTimeRoomOfChairDTO();
				mtr = movietimeroomService.findOneByIdMovieTimeRoom(idMovieTimeRoom);
				mav.addObject("mtr", mtr);
				mav.addObject("idUser", idUser);
				mav.addObject("fullName", fullName);
				mav.addObject("idMovie", idMovie);
				mav.addObject("idRoom", idRoom);
				mav.addObject("lst_chair", chairService.findChairByIdRoom(mtr.getIdRoom().getIdRoom()));
				List<ChairDTO> lst_bookedChair = chairService.getBookedChairs(idMovieTimeRoom);
				mav.addObject("lst_bookedChair", lst_bookedChair);
				List<ChairDTO> lst_unbookedChair = chairService.getUnbookedChairs(mtr.getIdRoom().getIdRoom(),
						idMovieTimeRoom);
				mav.addObject("lst_unbookedChair", lst_unbookedChair);
				List<Integer> lst_count = new ArrayList<Integer>();
				for (int i = 1; i <= 11; i++) {
					lst_count.add(i);
				}
				mav.addObject("lst_count", lst_count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@GetMapping(value = "/confirm-pickchair")
	public ModelAndView ConfirmPickChairs(
			@RequestParam(value = "idMovieTimeRoom", required = false) int idMovieTimeRoom,
			@RequestParam(value = "idUser", required = false) Integer idUser,
			@RequestParam(value = "idMovie", required = false) Integer idMovie,
			@RequestParam(value = "idRoom", required = false) Integer idRoom,
			@RequestParam(value = "selectedChairs", required = false) int[] ids,
			@RequestParam(value = "selectedChairs", required = false) String cid,
			MovieDTO movieDto,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView mav = new ModelAndView("customer/chooseCombo");
		try {
			// Độ dài của chuỗi số
	        int length = 8;
	        // Khởi tạo đối tượng Random
	        Random random = new Random();
	        // Tạo chuỗi số ngẫu nhiên
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < length; i++) {
	            // Tạo số ngẫu nhiên từ 0 đến 9 và thêm vào chuỗi
	            int digit = random.nextInt(10);
	            sb.append(digit);
	        }
	        //kiểm tra ticketCode đó có tồn tại trong data ko nếu tồn tại thì tạo ticketCode khác
	        String ticketCode = sb.toString();
			TicketDTO ticketDTO = new TicketDTO();
			ticketDTO.setIdUser(idUser);
			ticketDTO.setIdMovie(idMovie);
			ticketDTO.setTicketCode(ticketCode);
			
			List<Integer> ids_chairtimeIntegers = chairtimeService.addChairsTime(ids, idMovieTimeRoom);
			ticketService.addTicket(ticketDTO);
			
			int idTicket = ticketService.getLastInsertedTicketId();
			ChairTicketDTO chairTicketDTO = new ChairTicketDTO();
			for(Integer i : ids_chairtimeIntegers) {
				chairTicketDTO.setIdChairTime(i);
				chairTicketDTO.setIdTicket(idTicket);
				chairticketService.addChairTicket(chairTicketDTO);
			}
			session.setAttribute("idsChairTime", ids_chairtimeIntegers);
			mav.addObject("idUser", idUser);
			mav.addObject("idMovieTimeRoom", idMovieTimeRoom);
			mav.addObject("idMovie", idMovie);
			mav.addObject("idRoom", idRoom);
			mav.addObject("selected_idchairs",cid);
			
			
			List<ComboDTO> listcombo = comboService.getcombo();
			mav.addObject("lstcombo", listcombo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	@RequestMapping(value = "/choncombo", method = RequestMethod.GET)
	public ModelAndView HienThiCombo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("customer/chooseCombo");
		try {
			List<ComboDTO> listcombo = comboService.getcombo();
			mav.addObject("lstcombo", listcombo);
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			return mav;
		}
	}
	
}