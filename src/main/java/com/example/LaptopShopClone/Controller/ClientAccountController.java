package com.example.LaptopShopClone.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.LaptopShopClone.Entity.ChiTietDonHang;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.ServiceInterface.ChiTietDonHangService;
import com.example.LaptopShopClone.ServiceInterface.DonHangService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("loggedUser")
public class ClientAccountController {

	@Autowired 
	DonHangService donHangService;
	
	@ModelAttribute("loggedUser")
	public NguoiDung getLoggedUser(HttpServletRequest httpServletRequest) {
		return (NguoiDung)httpServletRequest.getSession().getAttribute("loggedUser");
	}
	
	@GetMapping("/profile")
	public String getProfilePage(Model model, @ModelAttribute("loggedUser") NguoiDung nguoiDung) {
		if(nguoiDung==null) {
			return "redirect:/login";
		}
		
		List<Integer> pageList=new ArrayList<Integer>();
		int noPage=4;
		int noResult=5;
		int totalNoResult=this.donHangService.getSoLuongDonHangByNguoiDung(nguoiDung, 0, noResult);
		int currentPage=1;
		
		for(int i=1; i<=noPage && i<=totalNoResult; ++i) {
			pageList.add(i);
		}
		
		List<ChiTietDonHang> donHangList=this.donHangService.getDonHangByNguoiDung(nguoiDung, 0, noResult);
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageList", pageList);
		model.addAttribute("donHangList", donHangList);
		
		return "Client/profile";
	}
	
	
	
	
	
}
