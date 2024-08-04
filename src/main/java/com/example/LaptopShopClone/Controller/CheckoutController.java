package com.example.LaptopShopClone.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.LaptopShopClone.Entity.ChiMucGioHang;
import com.example.LaptopShopClone.Entity.GioHang;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Entity.SanPham;
import com.example.LaptopShopClone.ServiceInterface.GioHangService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("loggedUser")


public class CheckoutController {
	@Autowired
	GioHangService gioHangService;
	
	
	@ModelAttribute("loggedUser")
	public NguoiDung getLoggedUser(HttpServletRequest httpServletRequest) {
		return (NguoiDung)httpServletRequest.getSession().getAttribute("loggedUser");
	}
	
	@GetMapping("/checkout")
	public String goToCheckOut(HttpServletRequest httpServletRequest, Model model) {
		NguoiDung nguoiDung=this.getLoggedUser(httpServletRequest);
		
		if(nguoiDung==null) {
			return "Client/checkout";
		}
		
		GioHang gioHang=this.gioHangService.getGioHangByNguoiDung(nguoiDung);
		List<ChiMucGioHang> chiMucGioHangList=gioHang.getChiMucGioHangs();
		List<SanPhamAndSoLuong> sanPhamAndSoLuongs=new ArrayList<SanPhamAndSoLuong>();
		Boolean isCartEmpty=true;
		List<String> errors=new ArrayList<String>();
		Boolean hasError=false;
		
		
		if(chiMucGioHangList.size()==0) {
			isCartEmpty=false;
			errors.add("Giỏ hàng trống");
			hasError=true;
			return "Client/checkout";
		}
		
		for(ChiMucGioHang chiMucGioHang:chiMucGioHangList) {
			sanPhamAndSoLuongs.add(new SanPhamAndSoLuong(chiMucGioHang.getSanPham(), chiMucGioHang.getSo_luong()));
		}
		
		model.addAttribute("sanPhamAndSoLuongs", sanPhamAndSoLuongs);
		model.addAttribute("isCartEmpty", isCartEmpty);
		model.addAttribute("nguoiDung", nguoiDung);
		model.addAttribute("errors", errors);
		model.addAttribute("hasError",hasError);
		
		
		
		return "Client/checkout";
	}
	
	@PostMapping("/xulycheckout")
	public String xulycheckout() {
		
	}
	
	@GetMapping("/test11")
	public String test1(Model model) {
		
		return "client/home";
		
	}
}
