package com.laptrinhjavaweb.utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class ConvertDate {
	public int toMonth(String strMonth) {
		int month = 1;
		if(strMonth.equals("January")) {
			month = 1;
		}else if(strMonth.equals("February")) {
			month = 2;
		} else if(strMonth.equals("March")) {
			month = 3;
		}else if(strMonth.equals("April")) {
			month = 4;
		}else if(strMonth.equals("May")) {
			month = 5;
		}else if(strMonth.equals("June")) {
			month = 6;
		}else if(strMonth.equals("July")) {
			month = 7;
		}else if(strMonth.equals("August")) {
			month = 8;
		}else if(strMonth.equals("September")) {
			month = 9;
		}else if(strMonth.equals("October")) {
			month = 10;
		}else if(strMonth.equals("November")) {
			month = 11;
		}else if(strMonth.equals("December")) {
			month = 12;
		}
		return month;
	}
	public LocalDate toDateUtil(String strdate) {
		//Date date = new Date();
		String[] sp = strdate.split("/");
		Integer d = Integer.parseInt(sp[0]);
		int dd = d.intValue();	// Ngày 
		int mm = toMonth(sp[1]);// Tháng
		Integer y = Integer.parseInt(sp[2]);
		int yy = y.intValue();
		LocalDate localDate = LocalDate.of(yy, mm, dd);

        // Định dạng lại LocalDate thành chuỗi mới
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		Calendar calendar = Calendar.getInstance();
//        calendar.set(yy, mm - 1, dd); 
//        Date date = calendar.getTime();
		return localDate;
	}
	public LocalDate toDate(String strdate) {
		String[] sp = strdate.split("/");
		Integer d = Integer.parseInt(sp[0]);
		int dd = d.intValue();	// Ngày 
		Integer m = Integer.parseInt(sp[1]);
		int mm =m.intValue();// Tháng
		Integer y = Integer.parseInt(sp[2]);
		int yy = y.intValue();
		LocalDate localDate = LocalDate.of(yy, mm, dd);
		return localDate;
	}
	
	public LocalDate getFirstDayOfMonth() {
		LocalDate today = LocalDate.now();
		return today.withDayOfMonth(1);
	}

	public LocalDate getLastDayOfMonth() {
		LocalDate today = LocalDate.now();
		YearMonth yearMonth = YearMonth.from(today);
		return yearMonth.atEndOfMonth();
	}
}