package com.laptrinhjavaweb.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.MovieTimeApiResponse;
import com.laptrinhjavaweb.dto.MovieTimeRoomDTO;
import com.laptrinhjavaweb.service.IMovieTimeRoomService;

@Controller
public class ShowTimeController {
	@Autowired
	private IMovieTimeRoomService movietimeservice;


	@GetMapping("/movieTimeList")
	public List<MovieTimeRoomDTO> getMovieTimeList() {
		try {
			return movietimeservice.getAllGroupByIdMovie1();
		} catch (Exception e) { // TODO Auto-generated catch block e.printStackTrace();
			return null;
		}
	}
	@RequestMapping(value = "/api/movie-time-list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<MovieTimeRoomDTO>> test() {
		try {

			List<MovieTimeRoomDTO> a = new ArrayList<>();
			MovieTimeRoomDTO b = new MovieTimeRoomDTO();

			List<MovieTimeRoomDTO> c = movietimeservice.getAllMovie();
			a.add(b);

			return new ResponseEntity<>(c, HttpStatus.OK);
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

	@RequestMapping(value = "/lichchieu", method = RequestMethod.GET)
	public ModelAndView showTime(@RequestParam(name = "selectedDate", required = false) String selectedDate) {
		ModelAndView mav = new ModelAndView("customer/timeCinema");
		try {
			// Lấy ngày hiện tại
			Date currentDate = new Date();

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);

			List<MovieTimeRoomDTO> listmovie;
			List<MovieTimeRoomDTO> listmovietime;
			if (selectedDate != null) {
				Date selectedDateObject = dateFormat.parse(selectedDate);
				listmovie = movietimeservice.getAllGroupByIdMovie(selectedDateObject);
			} else {
				listmovie = movietimeservice.getAllGroupByIdMovie(currentDate);
			}
			// listmovie = movietimeservice.getAllGroupByIdMovie();
			List<String> ngaychieuOptions = new ArrayList<>();

			ngaychieuOptions.add(dateFormat.format(currentDate));
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			ngaychieuOptions.add(dateFormat.format(calendar.getTime()));
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			ngaychieuOptions.add(dateFormat.format(calendar.getTime()));

			listmovietime = movietimeservice.getAllMovie();
			mav.addObject("length", listmovie.size());
			mav.addObject("listmovie", listmovie);
			mav.addObject("listmovietime", listmovietime);
			mav.addObject("selectedDate", (selectedDate != null) ? selectedDate : dateFormat.format(currentDate));
			mav.addObject("ngaychieuOptions", ngaychieuOptions);
//			for (MovieTimeRoomDTO m : listmovietime) {
//				m.getIdRoom();
//				m.getIdMovieTimeRoom();
//				m.getIdMovie().getIdMovie();
//			}
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			
			return mav;
		}
	}
	// fetch api de su dung schedule
		@GetMapping("/api/lichchieu")
		public ResponseEntity<Object> showTimeApi(@RequestParam(name = "selectedDate", required = false) String selectedDate) {
		    try {
		        // Lấy ngày hiện tại
		        Date currentDate = new Date();
		       // System.out.println(currentDate);
		        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		        List<MovieTimeRoomDTO> listmovie;
		        List<MovieTimeRoomDTO> listmovietime;
		        List<String> ngaychieuOptions = new ArrayList<>();

		        // Kiểm tra selectedDate trước khi sử dụng nó
		        Date selectedDateObject;
		        if (selectedDate != null && !selectedDate.isEmpty()) {
		            selectedDateObject = dateFormat.parse(selectedDate);
		            listmovie = movietimeservice.getAllGroupByIdMovie(selectedDateObject);
		        } else {
		            selectedDateObject = currentDate;
		            listmovie = movietimeservice.getAllGroupByIdMovie(currentDate);
		        }

		        ngaychieuOptions.add(dateFormat.format(currentDate));
		        Calendar calendar = Calendar.getInstance();
		        calendar.setTime(currentDate);
		        for (int i = 0; i < 2; i++) {
		            calendar.add(Calendar.DAY_OF_YEAR, 1);
		            ngaychieuOptions.add(dateFormat.format(calendar.getTime()));
		        }

		       
		        listmovietime = movietimeservice.getAllMovie();
		        
//		        for (MovieTimeRoomDTO temp : listmovietime) {
//					System.out.println(temp.getIdRoom());
//					System.out.println(temp.getIdMovie().getIdMovie());
//				}
		        
		        // Tạo đối tượng DTO để đại diện cho dữ liệu trả về
		        MovieTimeApiResponse apiResponse = new MovieTimeApiResponse();
		        apiResponse.setListmovie(listmovie);
		        apiResponse.setNgaychieuOptions(ngaychieuOptions);
		        apiResponse.setListmovietime(listmovietime);
		        apiResponse.setSelectedDate((selectedDate != null) ? selectedDate : dateFormat.format(currentDate));
		        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		    } catch (Exception e) {
		        e.printStackTrace();
		        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		}
}