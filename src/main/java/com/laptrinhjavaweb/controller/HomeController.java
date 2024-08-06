package com.laptrinhjavaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.service.IUserService;

@Controller
public class HomeController {
	

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView PageContact(Model model) {
		ModelAndView mav = new ModelAndView("customer/contact");
		return mav;
	}	

}