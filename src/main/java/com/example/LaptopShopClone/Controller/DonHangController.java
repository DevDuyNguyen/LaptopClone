package com.example.LaptopShopClone.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.LaptopShopClone.Entity.DonHang;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.ServiceInterface.DonHangService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("loggedUser")
public class DonHangController {
	@Autowired
	DonHangService donHangService;
	
	@ModelAttribute("loggedUser")
	public NguoiDung getLoggedUser(HttpServletRequest httpServletRequest) {
		return (NguoiDung)httpServletRequest.getSession().getAttribute("loggedUser");
	}
	
	@GetMapping("/detailOrder")
	public String getDetailOrder(@RequestParam("orderId") long orderId, Model model, @ModelAttribute("loggedUser") NguoiDung nguoiDung) {
		if(nguoiDung==null) {
			return "redirect:/home";
		}
		
		DonHang donHang=this.donHangService.getDonHangById(orderId);
		List<String> errors=new ArrayList<String>();
		
		if(donHang==null) {
			errors.add("Đơn hàng không tồn tại");
			model.addAttribute("errors", errors);
			return "/Client/detailOrder";
		}
		
		model.addAttribute("donHang", donHang);
		
		return "/Client/detailOrder";
	}
	
	
}
