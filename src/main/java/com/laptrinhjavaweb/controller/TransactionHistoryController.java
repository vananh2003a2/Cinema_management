package com.laptrinhjavaweb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.laptrinhjavaweb.dto.TicketDTO;
import com.laptrinhjavaweb.dto.TransactionHistoryDTO;
import com.laptrinhjavaweb.service.ITicketService;
import com.laptrinhjavaweb.service.ITransactionHistoryService;
import com.laptrinhjavaweb.utils.SecurityUtils;

@Controller
public class TransactionHistoryController {
	
	@Autowired
	private ITransactionHistoryService service;
	
	@Autowired
    private ITicketService ticketService;
	
	@RequestMapping(value ="/transaction", method = RequestMethod.GET)
	public ModelAndView homePage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("customer/transactionHistory");
		
		try {
			request.setAttribute("message", request.getParameter("message"));
			
			int id = SecurityUtils.getPrincipal().getIdUser();
	
			
			List<TransactionHistoryDTO> uniqueTickets = new ArrayList<>();
			List<TransactionHistoryDTO> listTr = service.getTransactionHistoryById(id);
			List<TransactionHistoryDTO> getdetail = service.getTransactionHistoryById(id);
	        mav.addObject("getdetail", getdetail);
			
			if (!listTr.isEmpty()) {
	            uniqueTickets.add(listTr.get(0));
	            int size = listTr.size();
	            String previousId = listTr.get(0).getTicketCode();
	            
	            for (int i = 1; i < size; i++) {
	                String currentId = listTr.get(i).getTicketCode();
	                if (!currentId.equals(previousId)) {
	                    uniqueTickets.add(listTr.get(i));
	                    previousId = currentId;
	                }
	            }
	        }

			mav.addObject("listTr", uniqueTickets);
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			return mav;
		}
	}
}


