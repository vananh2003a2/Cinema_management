package com.laptrinhjavaweb.controller.adminsystem;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.SecurityUtils;

@Controller
public class UserAccountController {
	@Autowired
	private IUserService userService;

	// phân trang nha mn
	@RequestMapping(value = "system/admin/account", method = RequestMethod.GET)
	public ModelAndView qladmin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("system/accounts");
		List<String> roles = SecurityUtils.getAuthorities();
		try {
			List<UserDTO> listuser;
			String key = request.getParameter("txtsearch");
			mav.addObject("key", key);
			int page = 0; // Trang mặc định
			int size = 5; // Số lượng phần tử trên mỗi trang
			String pageStr = request.getParameter("page");
			if (pageStr != null && !pageStr.isEmpty()) {
				page = Integer.parseInt(pageStr);
			}
			mav.addObject("currentPage", page);

			if (key == null || key.isEmpty()) {
				listuser = userService.getAllUser();
			} else {
				listuser = userService.getUserByName(key);
				
			}

			// Tính chỉ mục bắt đầu và kết thúc cho trang hiện tại
			int start = page * size;
			int end = Math.min(start + size, listuser.size());

			// Lấy chỉ mục từ start đến end của listuser
			List<UserDTO> paginatedUsers = listuser.subList(start, end);

			mav.addObject("listuser", paginatedUsers);
			mav.addObject("totalPages", (int) Math.ceil((double) listuser.size() / size));
			mav.addObject("totalrow", (int) Math.ceil((double) listuser.size()));
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			return mav;
		}
	}
	
	@GetMapping(value = "system/admin/lock-account")
	public ModelAndView LockAccount(@RequestParam(value = "idUser", required = false) int idUser) {
		userService.LockAccount(idUser);
		return new ModelAndView("redirect:/system/admin/account");
	}

//	@GetMapping(value = "system/admin/lock-account")
//	public void LockAccount(@RequestParam(value = "idUser", required = false) int idUser) {
//		userService.LockAccount(idUser);
//	}

	@RequestMapping(value = "system/admin/unlock-account", method = RequestMethod.GET)
	public ModelAndView UnlockAccount(@RequestParam(value = "idUser", required = false) int idUser) {
		userService.UnlockAccount(idUser);
		return new ModelAndView("redirect:/system/admin/account");
	}
//	@GetMapping(value = "system/admin/unlock-account")
//	public void unLockAccount(@RequestParam(value = "idUser", required = false) int idUser) {
//		userService.UnlockAccount(idUser);
//	}
}
