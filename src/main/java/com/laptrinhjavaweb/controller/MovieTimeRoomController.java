package com.laptrinhjavaweb.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.MovieDTO;
import com.laptrinhjavaweb.dto.MovieTimeRoomDTO;
import com.laptrinhjavaweb.dto.MovieTypeDTO;
import com.laptrinhjavaweb.dto.RoomDTO;
import com.laptrinhjavaweb.service.IMovieService;
import com.laptrinhjavaweb.service.IMovieTimeRoomService;
import com.laptrinhjavaweb.service.IMovieTypeService;
import com.laptrinhjavaweb.service.IRoomService;

@Controller(value = "adminMTRController")
public class MovieTimeRoomController {

	@Autowired
	private IRoomService roomService;

	@Autowired
	private IMovieTimeRoomService mtrService;

	@Autowired
	private IMovieService movieService;

	@Autowired
	private IMovieTypeService movietypeService;

//	@RequestMapping(value = "admin/time", method = RequestMethod.GET)
//	public ModelAndView searchMoviesPage(@RequestParam int page, @RequestParam int size, HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView("admin/time");
//		try {
//			String result = request.getParameter("result");
//			String strIdRoom = request.getParameter("idRoom");
//			int idRoom = 0;
//			if (!strIdRoom.equals(null)) {
//				idRoom = Integer.parseInt(strIdRoom);
//			}
//			String searchValue = request.getParameter("searchValue");
//			String dateRange = request.getParameter("dateRange");
//
//			if (page < 0) {
//				page = 0;
//			}
//
//			List<RoomDTO> lst_room = roomService.findAll();
//			mav.addObject("lst_room", lst_room);
//			List<MovieDTO> lst_movie = movieService.findAll();
//			mav.addObject("lst_movie", lst_movie);
//			List<MovieTypeDTO> lst_movietype = movietypeService.findAll();
//			mav.addObject("lst_movietype", lst_movietype);
//
//			List<MovieTimeRoomDTO> lst_mtr = new ArrayList<MovieTimeRoomDTO>();
//			long totalItems = 0;
//			if (searchValue.equals("") && idRoom == 0 && dateRange.equals("dd/MM/yyyy")) {
////				lst_mtr = mtrService.finaAll();
//				lst_mtr = mtrService.findAllPageble(page*5, size);
//				totalItems = mtrService.count();
//			} else {
//				if (!searchValue.equals("") && idRoom == 0 && dateRange.equals("dd/MM/yyyy")) {
//					// Tìm kiếm theo tên
//					lst_mtr = mtrService.searchByMovieName(searchValue, size, page*5);
//					totalItems = mtrService.countsearchByMovieName(searchValue);
//				} else if (searchValue.equals("") && idRoom != 0 && dateRange.equals("dd/MM/yyyy")) {
//					// Tìm kiếm theo phòng
//					lst_mtr = mtrService.searchByIdRoom(idRoom, size, page*5);
//					totalItems = mtrService.countsearchByIdRoom(idRoom);
//				} else if (searchValue.equals("") && idRoom == 0 && !dateRange.equals("dd/MM/yyyy")) {
//					// Tìm kiếm theo ngày
//					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//					Date date = sdf.parse(dateRange);
//					lst_mtr = mtrService.searchByDate(date, size, page*5);
//					totalItems = mtrService.countSearchByDate(date);
//				} else if (!searchValue.equals("") && idRoom != 0 && dateRange.equals("dd/MM/yyyy")) {
//					// Tìm kiếm theo tên phim và phòng
//					lst_mtr = mtrService.searchBy_movieName_IdRoom(searchValue, idRoom, size, page*5);
//					totalItems = mtrService.countSearchBy_movieName_IdRoom(searchValue, idRoom);
//				} else if (!searchValue.equals("") && idRoom == 0 && !dateRange.equals("dd/MM/yyyy")) {
//					// Tìm kiếm theo tên phim và ngày
//					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//					Date date = sdf.parse(dateRange);
//					lst_mtr = mtrService.searchBy_movieName_Date(searchValue, date, size, page*5);
//					totalItems = mtrService.countSearchBy_movieName_Date(searchValue, date);
//				} else if (searchValue.equals("") && idRoom != 0 && !dateRange.equals("dd/MM/yyyy")) {
//					// Tìm kiếm theo ngày và phòng
//					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//					Date date = sdf.parse(dateRange);
//					lst_mtr = mtrService.searchBy_IdRoom_Date(idRoom, date, size, page*5);
//					totalItems = mtrService.countSearchBy_IdRoom_Date(idRoom, date);
//				} else if (!searchValue.equals("") && idRoom != 0 && !dateRange.equals("dd/MM/yyyy")) {
//					// Tìm với tất cả input
//					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//					Date date = sdf.parse(dateRange);
//					lst_mtr = mtrService.searchBy_MovieName_Room_Date(searchValue, idRoom, date, size, page*5);
//					totalItems = mtrService.countSearchBy_MovieName_Room_Date(searchValue, idRoom, date);
//				}	
//			}			
//			int totalPages = totalItems > 0 ? (int) Math.ceil((double) totalItems / size) : 0;
//			// Xử lý trang hiện tại vượt quá tổng số trang
//			// Kiểm tra nếu page vượt quá totalPages hoặc totalPages là 0
//			
//			if (page >= totalPages || totalPages == 0) {
//				return new ModelAndView(
//						"redirect:/admin/time?page=0&size=5&searchValue=&dateRange=dd/MM/yyyy&idRoom=0&result=0");
//			}
//			mav.addObject("lst_mtr", lst_mtr);
//			mav.addObject("idRoom", idRoom);
//			mav.addObject("dateRange", dateRange);
//			mav.addObject("searchValue", searchValue);
//			mav.addObject("totalItems", totalItems);
//			mav.addObject("currentPage", page);
//			mav.addObject("pageSize", size);
//			mav.addObject("totalPages", totalPages);
//			mav.addObject("result", result);
//			if(lst_mtr.isEmpty()) {
//				result = "0";
//				return new ModelAndView(
//						mav+"?page=0&size=5&searchValue="+ searchValue+"&dateRange="+dateRange+"&idRoom="+idRoom+"&result="+result);
//			}
//			return mav;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return mav;
//	}
	@RequestMapping(value = "admin/time", method = RequestMethod.GET)
	public ModelAndView searchMoviesPage(@RequestParam int page, @RequestParam int size, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/time");
		try {
			String result = request.getParameter("result");
			String strIdRoom = request.getParameter("idRoom");
			int idRoom = 0;
			if (!strIdRoom.equals(null)) {
				idRoom = Integer.parseInt(strIdRoom);
			}
			String searchValue = request.getParameter("searchValue");
			String dateRange = request.getParameter("dateRange");

			if (page < 0) {
				page = 0;
			}

			List<RoomDTO> lst_room = roomService.findAll();
			mav.addObject("lst_room", lst_room);
			List<MovieDTO> lst_movie = movieService.findAll();
			mav.addObject("lst_movie", lst_movie);
			List<MovieTypeDTO> lst_movietype = movietypeService.findAll();
			mav.addObject("lst_movietype", lst_movietype);

			List<MovieTimeRoomDTO> lst_mtr = new ArrayList<MovieTimeRoomDTO>();
			long totalItems = 0;
			if (searchValue.equals("") && idRoom == 0 && dateRange.equals("")) {
//				lst_mtr = mtrService.finaAll();
				lst_mtr = mtrService.findAllPageble(page*5, size);
				totalItems = mtrService.count();
			} else {
				if (!searchValue.equals("") && idRoom == 0 && dateRange.equals("")) {
					// Tìm kiếm theo tên
					lst_mtr = mtrService.searchByMovieName(searchValue, size, page*5);
					totalItems = mtrService.countsearchByMovieName(searchValue);
				} else if (searchValue.equals("") && idRoom != 0 && dateRange.equals("")) {
					// Tìm kiếm theo phòng
					lst_mtr = mtrService.searchByIdRoom(idRoom, size, page*5);
					totalItems = mtrService.countsearchByIdRoom(idRoom);
				} else if (searchValue.equals("") && idRoom == 0 && !dateRange.equals("")) {
					// Tìm kiếm theo ngày
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date date = sdf.parse(dateRange);
					lst_mtr = mtrService.searchByDate(date, size, page*5);
					totalItems = mtrService.countSearchByDate(date);
				} else if (!searchValue.equals("") && idRoom != 0 && dateRange.equals("")) {
					// Tìm kiếm theo tên phim và phòng
					lst_mtr = mtrService.searchBy_movieName_IdRoom(searchValue, idRoom, size, page*5);
					totalItems = mtrService.countSearchBy_movieName_IdRoom(searchValue, idRoom);
				} else if (!searchValue.equals("") && idRoom == 0 && !dateRange.equals("")) {
					// Tìm kiếm theo tên phim và ngày
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date date = sdf.parse(dateRange);
					lst_mtr = mtrService.searchBy_movieName_Date(searchValue, date, size, page*5);
					totalItems = mtrService.countSearchBy_movieName_Date(searchValue, date);
				} else if (searchValue.equals("") && idRoom != 0 && !dateRange.equals("")) {
					// Tìm kiếm theo ngày và phòng
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date date = sdf.parse(dateRange);
					lst_mtr = mtrService.searchBy_IdRoom_Date(idRoom, date, size, page*5);
					totalItems = mtrService.countSearchBy_IdRoom_Date(idRoom, date);
				} else if (!searchValue.equals("") && idRoom != 0 && !dateRange.equals("")) {
					// Tìm với tất cả input
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date date = sdf.parse(dateRange);
					lst_mtr = mtrService.searchBy_MovieName_Room_Date(searchValue, idRoom, date, size, page*5);
					totalItems = mtrService.countSearchBy_MovieName_Room_Date(searchValue, idRoom, date);
				}	
			}			
			int totalPages = totalItems > 0 ? (int) Math.ceil((double) totalItems / size) : 0;
			// Xử lý trang hiện tại vượt quá tổng số trang
			// Kiểm tra nếu page vượt quá totalPages hoặc totalPages là 0
			
			if (page >= totalPages || totalPages == 0) {
				return new ModelAndView(
						"redirect:/admin/time?page=0&size=5&searchValue=&dateRange=&idRoom=0&result=0");
			}
			mav.addObject("lst_mtr", lst_mtr);
			mav.addObject("idRoom", idRoom);
			mav.addObject("dateRange", dateRange);
			mav.addObject("searchValue", searchValue);
			mav.addObject("totalItems", totalItems);
			mav.addObject("currentPage", page);
			mav.addObject("pageSize", size);
			mav.addObject("totalPages", totalPages);
			mav.addObject("result", result);
			if(lst_mtr.isEmpty()) {
				result = "0";
				return new ModelAndView(
						mav+"?page=0&size=5&searchValue="+ searchValue+"&dateRange="+dateRange+"&idRoom="+idRoom+"&result="+result);
			}
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "admin/time", method = RequestMethod.POST)
	public ModelAndView searchScreening(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam("idRoom") int idRoom,
			HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("admin/time");
		try {
			// String idRoom = request.getParameter("idRoom");
			String searchValue = request.getParameter("searchValue");
			String dateRange = request.getParameter("dateRange");

			if (page < 0) {
				page = 0;
			}
			List<RoomDTO> lst_room = roomService.findAll();
			mav.addObject("lst_room", lst_room);
			List<MovieDTO> lst_movie = movieService.findAll();
			mav.addObject("lst_movie", lst_movie);
			List<MovieTypeDTO> lst_movietype = movietypeService.findAll();
			mav.addObject("lst_movietype", lst_movietype);

			List<MovieTimeRoomDTO> lst_mtr = new ArrayList<MovieTimeRoomDTO>();
			long totalItems = 0;
			if (searchValue.equals("") && idRoom == 0 && dateRange.equals("dd/MM/yyyy")) {
//				lst_mtr = mtrService.finaAll();
				lst_mtr = mtrService.findAllPageble(page, size);
				totalItems = mtrService.count();
			} else {
				if (!searchValue.equals("") && idRoom == 0 && dateRange.equals("dd/MM/yyyy")) {
					// Tìm kiếm theo tên
					lst_mtr = mtrService.searchByMovieName(searchValue, size, page);
					totalItems = mtrService.countsearchByMovieName(searchValue);
				} else if (searchValue.equals("") && idRoom != 0 && dateRange.equals("dd/MM/yyyy")) {
					// Tìm kiếm theo phòng
					lst_mtr = mtrService.searchByIdRoom(idRoom, size, page);
					totalItems = mtrService.countsearchByIdRoom(idRoom);
				} else if (searchValue.equals("") && idRoom == 0 && !dateRange.equals("dd/MM/yyyy")) {
					// Tìm kiếm theo ngày
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date date = sdf.parse(dateRange);
					lst_mtr = mtrService.searchByDate(date, size, page);
					totalItems = mtrService.countSearchByDate(date);
				} else if (!searchValue.equals("") && idRoom != 0 && dateRange.equals("dd/MM/yyyy")) {
					// Tìm kiếm theo tên phim và phòng
					lst_mtr = mtrService.searchBy_movieName_IdRoom(searchValue, idRoom, size, page);
					totalItems = mtrService.countSearchBy_movieName_IdRoom(searchValue, idRoom);
				} else if (!searchValue.equals("") && idRoom != 0 && dateRange.equals("dd/MM/yyyy")) {
					// Tìm kiếm theo tên phim và ngày
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date date = sdf.parse(dateRange);
					lst_mtr = mtrService.searchBy_movieName_Date(searchValue, date, size, page);
					totalItems = mtrService.countSearchBy_movieName_Date(searchValue, date);
				} else if (!searchValue.equals("") && idRoom != 0 && dateRange.equals("dd/MM/yyyy")) {
					// Tìm kiếm theo ngày và phòng
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date date = sdf.parse(dateRange);
					lst_mtr = mtrService.searchBy_IdRoom_Date(idRoom, date, size, page);
					totalItems = mtrService.countSearchBy_IdRoom_Date(idRoom, date);
				} else if (!searchValue.equals("") && idRoom != 0 && !dateRange.equals("dd/MM/yyyy")) {
					// Tìm với tất cả input
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date date = sdf.parse(dateRange);
					lst_mtr = mtrService.searchBy_MovieName_Room_Date(searchValue, idRoom, date, size, page);
					totalItems = mtrService.countSearchBy_MovieName_Room_Date(searchValue, idRoom, date);
				}
			}
			
			int totalPages = totalItems > 0 ? (int) Math.ceil((double) totalItems / size) : 0;
			// Xử lý trang hiện tại vượt quá tổng số trang
			// Kiểm tra nếu page vượt quá totalPages hoặc totalPages là 0
			if (page >= totalPages || totalPages == 0) {
				return new ModelAndView("redirect:/admin/time");
			}
			mav.addObject("lst_mtr", lst_mtr);
			mav.addObject("idRoom", idRoom);
			mav.addObject("dateRange", dateRange);
			mav.addObject("searchValue", searchValue);
			mav.addObject("totalItems", totalItems);
			mav.addObject("currentPage", page);
			mav.addObject("pageSize", size);
			mav.addObject("totalPages", totalPages);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;

	}

	
}