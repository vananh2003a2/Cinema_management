package com.laptrinhjavaweb.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.laptrinhjavaweb.dto.MovieDTO;
import com.laptrinhjavaweb.dto.MovieTypeDTO;
import com.laptrinhjavaweb.entity.MovieEntity;
import com.laptrinhjavaweb.entity.MovieTypeEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.service.IMovieService;
import com.laptrinhjavaweb.service.IMovieTimeRoomService;
import com.laptrinhjavaweb.service.IMovieTypeService;

@Controller
public class MovieController {

	@Autowired
	private IMovieService movieService;
	@Autowired
	private IMovieTimeRoomService movieTimeRoomService;
	@Autowired
	private IMovieTypeService movietypeService;

	@RequestMapping(value = { "/index", "/" }, method = RequestMethod.GET)
	public ModelAndView homePage(HttpServletRequest request, HttpServletResponse response) {
		try {
			ModelAndView mav = new ModelAndView("customer/index");
			String message = request.getParameter("message");
			// Danh sách phim đang chiếu
			List<MovieDTO> lstMovieDTODangChieu = movieService.getMovieForStatus(1);
			// Danh sách phim sắp chiếu
			List<MovieDTO> lstMovieDTOSapChieu = movieService.getMovieForStatus(0);
			// Danh sách 5 phim bán chạy nhất để chiếu slide
			List<MovieDTO> lstMovieDTOTOP = movieService.getMovieTop();
			request.setAttribute("message", message);
			request.setAttribute("lstMovieDTODangChieu", lstMovieDTODangChieu);
			request.setAttribute("lstMovieDTOSapChieu", lstMovieDTOSapChieu);
			request.setAttribute("lstMovieDTOTOP", lstMovieDTOTOP);
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			ModelAndView mav = new ModelAndView("customer/index");
			return mav;
		}
	}

	// giao dien chinh cua cac phim dang chieu
	@RequestMapping(value = "/phimdangchieu", method = RequestMethod.GET)
	public ModelAndView pagePhimDangChieu(HttpServletRequest request, HttpServletResponse response) {
		try {
			ModelAndView mav = new ModelAndView("customer/phimChieu");
			// Danh sách phim đang chiếu

			List<MovieDTO> lstMovieDTODangChieu = movieService.getMovieForStatus(1);
			request.setAttribute("status", "dangChieu");
			request.setAttribute("lstMovieDTODangChieu", lstMovieDTODangChieu);

			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			ModelAndView mav = new ModelAndView("customer/index");
			return mav;
		}
	}

	// giao dien chinh cua cac phim sap chieu
	@RequestMapping(value = "/phimsapchieu", method = RequestMethod.GET)
	public ModelAndView pagePhimSapChieu(HttpServletRequest request, HttpServletResponse response) {
		try {
			ModelAndView mav = new ModelAndView("customer/phimChieu");
			// Danh sách phim đang chiếu

			List<MovieDTO> lstMovieDTOSapChieu = movieService.getMovieForStatus(0);
			request.setAttribute("status", "sapChieu");
			request.setAttribute("lstMovieDTOSapChieu", lstMovieDTOSapChieu);

			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			ModelAndView mav = new ModelAndView("customer/index");
			return mav;
		}
	}

	// Quản lý phim
	@RequestMapping(value = "/admin/searchMovie", method = RequestMethod.GET)
	public ModelAndView searchMoviesGet(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("admin/movie");
		try {
			String idMovieType = request.getParameter("idMovieType");
			String searchValue = request.getParameter("searchValue");
			String movieDate = request.getParameter("movieDate");
			
			if (page < 0) {
				page = 0;
			}

			Date date = null;
			if (movieDate != null && !movieDate.isEmpty()) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					date = sdf.parse(movieDate);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			List<MovieEntity> listMovie = new ArrayList<MovieEntity>();
			long totalItems = 0;
			if (!idMovieType.isEmpty() && !movieDate.isEmpty() && !searchValue.isEmpty()) {
			    listMovie = movieService.searchMovies(searchValue, Integer.parseInt(idMovieType), date, page, size);
			    totalItems = movieService.getTotalItemsBySearch(Integer.parseInt(idMovieType), date, searchValue);
			    
			}else if (!idMovieType.isEmpty() && !movieDate.isEmpty() && searchValue.isEmpty()) {
			    // 2 idMovieType và movieDate
			    listMovie = movieService.searchMovieByIDTypeAndMovieDate(Integer.parseInt(idMovieType), date, page, size);
			    totalItems = movieService.totalItemsByIdTypeAndMovieDate(Integer.parseInt(idMovieType),date);
			}else if (!idMovieType.isEmpty() && movieDate.isEmpty() && !searchValue.isEmpty()) {
			    // 2 idMovieType và searchValue
			    listMovie = movieService.searchMovieByIDTypeAndSearchValue(searchValue,Integer.parseInt(idMovieType), page, size);
			    totalItems = movieService.totalItemsByIdTypeAndSearchValue(Integer.parseInt(idMovieType),searchValue);
			}else if (idMovieType.isEmpty() && !movieDate.isEmpty() && !searchValue.isEmpty()) {
			    // 2 movieDate và searchValue
			    listMovie = movieService.searchMovieBySearchValueAndMovieDate(searchValue,date, page, size);
			    totalItems = movieService.totalItemsByMovieDateAndSearchValue(date,searchValue);
			}else if (!idMovieType.isEmpty() && movieDate.isEmpty() && searchValue.isEmpty()) {
			    //id
			    listMovie = movieService.searchMovieByIdType(Integer.parseInt(idMovieType), page, size);
			    totalItems = movieService.getTotalItemsByIdType(Integer.parseInt(idMovieType));
			}else if (idMovieType.isEmpty() && !movieDate.isEmpty() && searchValue.isEmpty()) {
			    // date
			    listMovie = movieService.searchMovieByMovieDate(date, page, size);
			    totalItems = movieService.getTotalItemsByDate(date);
			}else if (idMovieType.isEmpty() && movieDate.isEmpty() && !searchValue.isEmpty()) {
			    //value
			    listMovie = movieService.searchMovieByMovieName(searchValue, page, size);
			    totalItems = movieService.getTotalItemsByName(searchValue);
			}else {
			    listMovie = movieService.findAll(page, size);
			    totalItems = movieService.getTotalItems();
			}
			
			boolean[] check = new boolean[listMovie.size()];
			int i = 0;
			for (MovieEntity movie : listMovie) {
				int checkMovie = movieTimeRoomService.checkMovieIsUsed(movie.getIdMovie());
				if(checkMovie == 0) {
					check[i] = true;
				}
				else {
					check[i] = false;
				}
				i++;
			}

			int totalPages = totalItems > 0 ? (int) Math.ceil((double) totalItems / size) : 0;
			

			
			boolean isListMovieEmpty = listMovie.isEmpty();
			System.out.println(isListMovieEmpty);
	        mav.addObject("isListMovieEmpty", isListMovieEmpty);
	        
			List<MovieTypeDTO> listMovieType = movietypeService.findAll();
			mav.addObject("checkMovie", check);
			mav.addObject("idMovieType", idMovieType);
			mav.addObject("movieDate", movieDate);
			mav.addObject("searchValue", searchValue);
			
			mav.addObject("lstmovie", listMovie);
			mav.addObject("lstmovietype", listMovieType);
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

	@RequestMapping(value = "/admin/qlmovie", method = RequestMethod.GET)
	public ModelAndView searchMoviesPage(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(value = "message", required = false) String message) throws Exception{
		ModelAndView mav = new ModelAndView("admin/movie");
		if(message != null) {
			mav.addObject("message", message);
		}
		if (page < 0) {
			page = 0;
		}
		boolean[] check = new boolean[movieService.findAll(page, size).size()];
		int i = 0;
		for (MovieEntity movie : movieService.findAll(page, size)) {
			int checkMovie = movieTimeRoomService.checkMovieIsUsed(movie.getIdMovie());
			if(checkMovie == 0) {
				check[i] = true;
			}
			else {
				check[i] = false;
			}
			i++;
		}
		List<MovieEntity> listMovie = new ArrayList<MovieEntity>();
		long totalItems = 0;

		listMovie = movieService.findAll(page, size);
		totalItems = movieService.getTotalItems();

		int totalPages = totalItems > 0 ? (int) Math.ceil((double) totalItems / size) : 0;
		// Xử lý trang hiện tại vượt quá tổng số trang
		// Kiểm tra nếu page vượt quá totalPages hoặc totalPages là 0
		if (page >= totalPages || totalPages == 0) {
			return new ModelAndView("redirect:/admin/qlmovie");
		}
		List<MovieTypeDTO> listMovieType = movietypeService.findAll();
		mav.addObject("checkMovie", check);
		mav.addObject("lstmovie", listMovie);
		mav.addObject("lstmovietype", listMovieType);
		mav.addObject("totalItems", totalItems);
		mav.addObject("currentPage", page);
		mav.addObject("pageSize", size);
		mav.addObject("totalPages", totalPages);
		return mav;

	}
	@RequestMapping(value = "/admin/deletemovie", method = RequestMethod.GET)
	public String deleteMovie(HttpServletRequest request, RedirectAttributes redirectAttributes ) throws Exception{
		int idMovie =Integer.parseInt(request.getParameter("idMovie"));
		
		int check = movieService.deleteMovieIfNotUsed(idMovie);
		if (check == 1) {
			return "redirect:/admin/qlmovie";
		}
		return "redirect:/admin/qlmovie";
	}
}
