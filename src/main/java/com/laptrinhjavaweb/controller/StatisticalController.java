package com.laptrinhjavaweb.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.DailyRevenueDTO;
import com.laptrinhjavaweb.dto.RevenueMovieDTO;
import com.laptrinhjavaweb.dto.RevenueScreeningDTO;
import com.laptrinhjavaweb.dto.TicketOfMovieDTO;
import com.laptrinhjavaweb.service.IDailyRevenueService;
import com.laptrinhjavaweb.service.IRevenueMovieService;
import com.laptrinhjavaweb.service.IRevenueScreeningService;
import com.laptrinhjavaweb.service.ITicketOfMovieService;
import com.laptrinhjavaweb.utils.ConvertDate;

@Controller(value = "statisticalController")
public class StatisticalController {

	@Autowired
	private IDailyRevenueService dailyrevenueService;

	@Autowired
	private IRevenueMovieService revenuemovieService;

	@Autowired
	private IRevenueScreeningService revenuescreeningService;

	@Autowired
	private ITicketOfMovieService tomService;

	@RequestMapping(value = "admin/index", method = RequestMethod.GET)
	public ModelAndView homePage(Model model) {
		ModelAndView mav = new ModelAndView("admin/index");
		ConvertDate convert = new ConvertDate();
		LocalDate from = convert.getFirstDayOfMonth();
		LocalDate to = convert.getLastDayOfMonth();
//		LocalDate from = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
//		LocalDate to = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String strfrom = from.format(formatter);
        String strto = to.format(formatter);
        mav.addObject("fromDate", strfrom);
        mav.addObject("toDate", strto);
		Date fromDate = java.sql.Date.valueOf(from);
		Date toDate = java.sql.Date.valueOf(to);
		List<DailyRevenueDTO> lst_dailyrevenue = dailyrevenueService.findAll(fromDate, toDate);
		mav.addObject("lst_dailyrevenue", lst_dailyrevenue);
		// Tạo danh sách thống kê doanh thu phim
		List<RevenueMovieDTO> lst_rvmovie = revenuemovieService.revenueAllMovie();
		mav.addObject("lst_rvmovie", lst_rvmovie);
		// Tạo danh sạc thống kê suất chiếu
		List<RevenueScreeningDTO> lst_screening = revenuescreeningService.revenueScreening();
		mav.addObject("lst_screening", lst_screening);
		// Tạo danh sạc thống kê vé theo phim
		List<TicketOfMovieDTO> lst_tom = tomService.showTicketOfMovie();
		mav.addObject("lst_tom", lst_tom);
		for (DailyRevenueDTO dr : lst_dailyrevenue) {
			System.out.println(dr.getDate());
			System.out.println(dr.getTotal());
		}
		return mav;
	}
	@RequestMapping(value = "/admin/index", method = RequestMethod.POST)
	public ModelAndView DailyRevenue(@RequestParam("start-date") String fromDate,
			@RequestParam("end-date") String toDate) {
		ModelAndView mav = new ModelAndView("admin/index");
		try {
			ConvertDate formatDate = new ConvertDate();
			LocalDate frlocal = formatDate.toDate(fromDate);
			LocalDate tdlocal = formatDate.toDate(toDate);
			if (frlocal.isAfter(tdlocal)) {
			    mav.addObject("message_date", "Ngày bắt đầu phải trước hoặc bằng ngày kết thúc.");
			
			} else {
				Date frdate = java.sql.Date.valueOf(frlocal);
				Date tdate = java.sql.Date.valueOf(tdlocal);
				mav.addObject("fromDate", fromDate);
				mav.addObject("toDate", toDate);
				long diffInMillies = Math.abs(tdate.getTime() - frdate.getTime());
				long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				List<Date> lst_date = new ArrayList<Date>();
				for (int i = 0; i <= diff; i++) {
					LocalDate nextLocalDate = frlocal.plusDays(i);
					// Chuyển đổi lại từ LocalDate sang Date
					Date nextDate = java.sql.Date.valueOf(nextLocalDate);
					lst_date.add(nextDate);
				}
				mav.addObject("lst_date", lst_date);
				List<DailyRevenueDTO> lst_dailyrevenue = dailyrevenueService.findAll(frdate, tdate);
				mav.addObject("lst_dailyrevenue", lst_dailyrevenue);
			}
			// Tạo danh sách thống kê doanh thu phim
			List<RevenueMovieDTO> lst_rvmovie = revenuemovieService.revenueAllMovie();
			mav.addObject("lst_rvmovie", lst_rvmovie);
			// Tạo danh sạc thống kê suất chiếu
			List<RevenueScreeningDTO> lst_screening = revenuescreeningService.revenueScreening();
			mav.addObject("lst_screening", lst_screening);
			// Tạo danh sạc thống kê vé theo phim
			List<TicketOfMovieDTO> lst_tom = tomService.showTicketOfMovie();
			mav.addObject("lst_tom", lst_tom);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
}