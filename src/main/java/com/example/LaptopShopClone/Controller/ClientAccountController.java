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

import com.example.LaptopShopClone.Entity.ChiTietDonHang;
import com.example.LaptopShopClone.Entity.DonHang;
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
	public String getProfilePage(Model model, @ModelAttribute("loggedUser") NguoiDung nguoiDung, @RequestParam(name = "currentPage", defaultValue = "1") int currentPage) {
		if(nguoiDung==null) {
			return "redirect:/login";
		}
		
		List<Integer> pageList=new ArrayList<Integer>();
		int noPage=5;
		int halfNoPage=(int)Math.ceil((double)noPage/2);
		int noResult=5;
		int totalNoResult=this.donHangService.getSoLuongDonHangByNguoiDung(nguoiDung);
		int totalPage=(int)Math.ceil((double)totalNoResult/noResult);
		
		
		if(currentPage==1 || currentPage==2 || currentPage==3 || currentPage==4 ||currentPage==5) {
			for(int i=1; i<=noPage && i<=totalPage; ++i) {
				pageList.add(i);
			}
		}
		else if(currentPage==totalPage) {
			for(int i=currentPage-1; i>(totalPage-noPage) && i>0; --i) {
				pageList.add(i);
			}
		}
		else {
			int tmpCurrentPage=currentPage;
			//left pages
			for(int i=1; i<=halfNoPage; ++i) {
				pageList.add(tmpCurrentPage-i);
			}
			
			pageList.add(currentPage);
			
			tmpCurrentPage=currentPage;
			//right pages
			for(int i=1; i<=halfNoPage; ++i) {
				pageList.add(tmpCurrentPage+i);
			}
		}
		
		List<DonHang> donHangList=this.donHangService.getDonHangByNguoiDung(nguoiDung, (currentPage-1)*noResult, noResult);
		List<Long> orderTotalList=new ArrayList<Long>();
		for(DonHang donHang: donHangList) {
			orderTotalList.add(this.donHangService.getOrderTotal(donHang));
		}
		
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageList", pageList);
		model.addAttribute("donHangList", donHangList);
		model.addAttribute("orderTotalList", orderTotalList);
		model.addAttribute("totalPage", totalPage);
		
		return "Client/profile";
	}
	
	
	
	
	
}
