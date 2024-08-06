package com.laptrinhjavaweb.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.MovieEntityShowTime;
import com.laptrinhjavaweb.dto.MovieTimeDetailApiResponse;
import com.laptrinhjavaweb.dto.MovieTimeRoomDTO;
import com.laptrinhjavaweb.service.IMovieService;
import com.laptrinhjavaweb.service.IMovieTimeRoomService;

@Controller
public class ShowTimeDetailController {
	@Autowired
	private IMovieTimeRoomService movietimeservice;

	@Autowired
	private IMovieService movieservice;

	@RequestMapping(value = "/showtime-detail", method = RequestMethod.GET)
	public ModelAndView showTime(@RequestParam(name = "idMovie", required = false) Integer idMovie) {
		ModelAndView mav = new ModelAndView("customer/showTime");
		try {
			if (idMovie != null && movieservice != null) { // Kiểm tra idMovie và movieservice không null
				// Lấy ngày hiện tại
				Date currentDate = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(currentDate);

				MovieEntityShowTime movie = movieservice.getMovieById(idMovie);
				List<MovieTimeRoomDTO> listmovietime = movietimeservice.getAllMovie();

				List<String> ngaychieus = new ArrayList<>();
				ngaychieus.add(dateFormat.format(currentDate));
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				ngaychieus.add(dateFormat.format(calendar.getTime()));
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				ngaychieus.add(dateFormat.format(calendar.getTime()));

				mav.addObject("idMovie", idMovie);
				mav.addObject("movie", movie);
				mav.addObject("listmovietime", listmovietime);
				mav.addObject("ngaychieus", ngaychieus);
			} else {
				mav.addObject("errorMessage", "Không tìm thấy thông tin về phim."); // Thông báo lỗi khi không tìm thấy
																					// idMovie hoặc movieservice
			}
		} catch (Exception e) {
			mav.addObject("errorMessage", "Có lỗi xảy ra khi tìm kiếm thông tin về phim.");
			e.printStackTrace();
		}
		return mav;
	}

	// fetch api de su dung schedule
	@GetMapping("/api/lichchieuchitiet")
	public ResponseEntity<Object> showTimeApi(@RequestParam(name = "idMovie", required = false) Integer idMovie) {
		try {
			MovieTimeDetailApiResponse apiResponse = new MovieTimeDetailApiResponse();
			if (idMovie != null && movieservice != null) { // Kiểm tra idMovie và movieservice không null
				// Lấy ngày hiện tại
				Date currentDate = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(currentDate);

				MovieEntityShowTime movie = movieservice.getMovieById(idMovie);
				List<MovieTimeRoomDTO> listmovietime = movietimeservice.getAllMovie();

				List<String> ngaychieus = new ArrayList<>();
				ngaychieus.add(dateFormat.format(currentDate));
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				ngaychieus.add(dateFormat.format(calendar.getTime()));
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				ngaychieus.add(dateFormat.format(calendar.getTime()));
				apiResponse.setIdMovie(idMovie);
				apiResponse.setListmovietime(listmovietime);
				apiResponse.setNgaychieus(ngaychieus);
				return new ResponseEntity<>(apiResponse, HttpStatus.OK);
			}
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}