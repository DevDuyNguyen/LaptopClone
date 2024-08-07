package com.example.LaptopShopClone.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.LaptopShopClone.Entity.ChiTietDonHang;
import com.example.LaptopShopClone.Entity.DonHang;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.ServiceInterface.ChiTietDonHangService;
import com.example.LaptopShopClone.ServiceInterface.DonHangService;
import com.example.LaptopShopClone.ServiceInterface.NguoiDungService;
import com.example.LaptopShopClone.Utils.ResponseObject;


import jakarta.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("loggedUser")
public class ClientAccountController {

	@Autowired 
	DonHangService donHangService;
	
	@Autowired
	NguoiDungService nguoiDungService;
	
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
	
	
	@GetMapping("/updateProfile")
	@ResponseBody
	public ResponseEntity<ResponseObject> updateProfile(Model model, 
			@ModelAttribute("loggedUser") NguoiDung nguoiDung, 
			@RequestParam("name") String name,
			@RequestParam("phone") String phone,
			@RequestParam("address") String address) {
		
		
		ResponseEntity<ResponseObject> re=null;
		ResponseObject ro=new ResponseObject();
		
		if(nguoiDung==null) {
			return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header("Location", "/login").body(null);
		}
		
		Pattern pattern=Pattern.compile("^\\d{10}$");
		Matcher matcher=pattern.matcher(phone);
		if(!matcher.matches()) {
			ro.getErrors().add("Số điện thoại không phù hợp");
			ro.setStatus("fail");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(ro);
		}
		
		if(!name.equals("") || name!=null) {
			nguoiDung.setHoTen(name);
		}
		if(!phone.equals("") || phone!=null) {
			nguoiDung.setSoDienThoai(phone);
		}
		if(!address.equals("") || address!=null) {
			nguoiDung.setDiaChi(address);
		}
		
		this.nguoiDungService.SaveOrUpdate(nguoiDung);
		ro.setStatus("success");
		re=ResponseEntity.status(HttpStatus.ACCEPTED).body(ro);
		
		return re;
		
	}
	
	
	
}
