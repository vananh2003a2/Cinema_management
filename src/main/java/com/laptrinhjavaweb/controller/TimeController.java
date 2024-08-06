package com.laptrinhjavaweb.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.MovieDTO;
import com.laptrinhjavaweb.dto.MovieTimeRoomOfChairDTO;
import com.laptrinhjavaweb.dto.RoomDTO;
import com.laptrinhjavaweb.dto.TimeRange;
import com.laptrinhjavaweb.dto.TimeRequestDTO;
import com.laptrinhjavaweb.dto.TimeUpdateDTO;
import com.laptrinhjavaweb.service.IMovieService;
import com.laptrinhjavaweb.service.IMovieTimeRoomService;
import com.laptrinhjavaweb.service.IRoomService;

@RestController
public class TimeController {
	@Autowired
	private IMovieTimeRoomService movieTimeRoomService;
	@Autowired
	private IMovieService movieService;
	@Autowired
	private IRoomService roomService;

	@RequestMapping(value = "admin/time_add", method = RequestMethod.GET)
	public ModelAndView TimeAddGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			ModelAndView mav = new ModelAndView("admin/time_add");
			List<RoomDTO> lstRoom = roomService.findAll();
			List<MovieDTO> lstMovie = movieService.findAll();
			String status = "false";
			request.setAttribute("status", status);
			request.setAttribute("lstMovie", lstMovie);
			request.setAttribute("lstRoom", lstRoom);
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			ModelAndView v = new ModelAndView("admin/index");
			return v;
		}
	}

	@RequestMapping(value = "admin/time_add", method = RequestMethod.POST)
	public ModelAndView TimeAddPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			ModelAndView mav = new ModelAndView("admin/time_add");
			List<RoomDTO> lstRoom = roomService.findAll();
			List<MovieDTO> lstMovie = movieService.findAll();
			String status = "false";
			String idRoom = request.getParameter("roomName");
			String idMovie = request.getParameter("movieName");
			String dateView = request.getParameter("dateView");
			String timeInput = request.getParameter("timeInput");
			// System.out.println(idMovie + "-" + idRoom);
			if (idRoom != null && idMovie != null && dateView != null) {
				MovieDTO movie = movieService.findByIdMovie(Integer.parseInt(idMovie));
				RoomDTO room = roomService.findfindByIdRoom(Integer.parseInt(idRoom));
				SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
				Date dateInput = f.parse(dateView);
				List<MovieTimeRoomOfChairDTO> newShowTimes = movieTimeRoomService.listTime(dateInput,
						Integer.parseInt(idRoom), Integer.parseInt(idMovie), timeInput);
				status = "true";
				request.setAttribute("newShowTimes", newShowTimes);
				request.setAttribute("status", status);
				request.setAttribute("lstMovie", lstMovie);
				request.setAttribute("lstRoom", lstRoom);
				request.setAttribute("movie", movie);
				request.setAttribute("room", room);
				request.setAttribute("dateView", dateView);
				request.setAttribute("timeInput", timeInput);
				return mav;
			}
			request.setAttribute("status", status);
			request.setAttribute("lstMovie", lstMovie);
			request.setAttribute("lstRoom", lstRoom);
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			ModelAndView v = new ModelAndView("admin/index");
			return v;
		}

	}

	// Api để lưu suất chiếu bằng AJAX
	@PostMapping(value = "/admin/saveTime", produces = "text/plain;charset=UTF-8")
	public ResponseEntity<Object> saveTime(@RequestBody TimeRequestDTO timeRequest, HttpServletRequest request) {
		try {
			String[] checkedValues = timeRequest.getCheckedValues();
			String movieId = timeRequest.getMovieId();
			String roomId = timeRequest.getRoomId();
			String dateView = timeRequest.getDateView();
			String timeInput = timeRequest.getTimeInput();
			MovieDTO movie = movieService.findByIdMovie(Integer.parseInt(movieId));
			RoomDTO room = roomService.findfindByIdRoom(Integer.parseInt(roomId));
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
			Date dateInput = f.parse(dateView);
			List<MovieTimeRoomOfChairDTO> newShowTimes = movieTimeRoomService.listTime(dateInput,
					Integer.parseInt(roomId), Integer.parseInt(movieId), timeInput);
			movieTimeRoomService.saveTime(newShowTimes, checkedValues);
			// Trả về thông báo lưu thành công cho client
			String successMessage = "Tạo thành công!";
			return new ResponseEntity<>(successMessage, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("", HttpStatus.OK);

		}
	}

	@RequestMapping(value = "admin/time_edit", method = RequestMethod.GET)
	public ModelAndView timeUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			ModelAndView mav = new ModelAndView("admin/time_edit");
			String idMovieTimeRoom = request.getParameter("idMovieTimeRoom");
			MovieTimeRoomOfChairDTO movieTimeRoomDTO = movieTimeRoomService
					.findByIdMovieTimeRoom(Integer.parseInt(idMovieTimeRoom));// Suất cần cập nhật
			List<MovieTimeRoomOfChairDTO> showTimesAll = movieTimeRoomService.listTimeRange(movieTimeRoomDTO.getDate(),
					movieTimeRoomDTO.getIdRoom().getIdRoom(), movieTimeRoomDTO.getIdMovie().getIdMovie());// Danh sach
																											// tat ca
																											// cac suat
																											// theo ngay
																											// chieu va
																											// phong
			List<MovieTimeRoomOfChairDTO> lstTimeConvert = movieTimeRoomService.listConvertTime(showTimesAll,
					movieTimeRoomDTO);// Danh sach da loai di suat can cap nhat
			List<TimeRange> lstTimeRange = movieTimeRoomService.findTimeBegin(lstTimeConvert, movieTimeRoomDTO);// Danh
																												// sách
																												// các
																												// khoảng
																												// thời
																												// gian
																												// có
																												// thể
																												// chỉnh
																												// sửa
																												// timeBegin
																												// của
																												// suất
//			for (TimeRange timeRange : lstTimeRange) {
//				System.out.println(timeRange.getTimeBegin().toString() + "-" + timeRange.getTimeEnd().toString());
//			}
			request.setAttribute("movieTimeRoom", movieTimeRoomDTO);// Gửi thông tin suất chiếu cần edit
			request.setAttribute("lstTimeRange", lstTimeRange); // Gửi danh sách thời gian bắt đầu hợp lệ
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			ModelAndView v = new ModelAndView("admin/index");
			return v;
		}
	}

	// Api để lưu suất chiếu sau khi cập nhật bằng AJAX
	@PostMapping(value = "/admin/saveTimeUpdate", produces = "text/plain;charset=UTF-8")
	public ResponseEntity<Object> saveTimeUpdate(@RequestBody TimeUpdateDTO timeRequest) {
		try {
	
			String idMovieTimeRoom = timeRequest.getIdMovieTimeRoom();
			String timeBegin = timeRequest.getTimeBegin();
			String timeEnd = timeRequest.getTimeEnd();
			movieTimeRoomService.saveTimeUpdate(idMovieTimeRoom, timeBegin, timeEnd);
			String successMessage = "Cập nhật thành công!";
			return new ResponseEntity<>(successMessage, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("", HttpStatus.OK);

		}
	}
}