package com.example.LaptopShopClone.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.LaptopShopClone.Entity.ChiMucGioHang;
import com.example.LaptopShopClone.Entity.ChiTietDonHang;
import com.example.LaptopShopClone.Entity.DonHang;
import com.example.LaptopShopClone.Entity.GioHang;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Entity.SanPham;
import com.example.LaptopShopClone.ServiceInterface.ChiMucGioHangService;
import com.example.LaptopShopClone.ServiceInterface.ChiTietDonHangService;
import com.example.LaptopShopClone.ServiceInterface.DonHangService;
import com.example.LaptopShopClone.ServiceInterface.GioHangService;
import com.example.LaptopShopClone.ServiceInterface.SanPhamService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("loggedUser")


public class CheckoutController {
	@Autowired
	GioHangService gioHangService;
	@Autowired
	DonHangService donHangService;
	@Autowired
	ChiTietDonHangService chiTietDonHangService;
	@Autowired 
	ChiMucGioHangService chiMucGioHangService;
	@Autowired
	SanPhamService sanPhamService;
	
	
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
	
	/*
	 <p style="line-height:2;" >Họ tên người nhận hàng *</p>
	<input size="27" name="hoTenNguoiNhan" id="hoTenNguoiNhan" required>
	
	<p style="line-height:2;">Số điện thoại *</p>
	<input size="27" name="sdtNhanHang" id="sdtNhanHang" required>
	
	
	<p style="line-height:2;">Địa chỉ(số nhà, đường, tỉnh thành) *</p>
	<textarea rows="5" cols="29" name="diaChiNhan" id="diaChiNhan" required></textarea
	 * 
	 */
	@PostMapping("/xulycheckout")
	public String xulycheckout(@RequestParam("hoTenNguoiNhan") String hoTenNguoiNhan,
			@RequestParam("sdtNhanHang") String sdtNhanHang,
			@RequestParam("diaChiNhan") String diaChiNhan,
			Model model,
			HttpServletRequest httpServletRequest){
		
		NguoiDung nguoiDung=this.getLoggedUser(httpServletRequest);
		List<String> errors= new ArrayList<String>();
		Boolean hasError=false;
		if(nguoiDung==null) {
			errors.add("Vui lòng đăng nhập tài khoản");
			model.addAttribute("errors", errors);
			hasError=true;
			model.addAttribute("hasError", hasError);
			return "client/thankyou";
			
		}
		GioHang gioHang=this.gioHangService.getGioHangByNguoiDung(nguoiDung);
		List<ChiMucGioHang> chiMucGioHangList=gioHang.getChiMucGioHangs();
		if(chiMucGioHangList.size()==0) {
			errors.add("Giỏ hàng trống");
			model.addAttribute("errors", errors);
			hasError=true;
			model.addAttribute("hasError", hasError);
			return "client/thankyou";
		}
		
		
		DonHang donHang=new DonHang();
		donHang.setHoTenNguoiNhan(hoTenNguoiNhan);
		donHang.setSdtNhanHang(sdtNhanHang);
		donHang.setDiaChiNhan(diaChiNhan);
		donHang.setNguoiDat(nguoiDung);
		donHang.setNgayDatHang(new Date());
		donHang.setTrangThaiDonHang("Đang chờ xử lý");
		donHang=this.donHangService.SaveOrUpdate(donHang);
		
		List<ChiTietDonHang> chiTietDonHangList=new ArrayList<ChiTietDonHang>();
		List<SanPham> sanPhamList=new ArrayList<SanPham>();
		
		//tao chitietdonhang tu chimucgiohang
		for(ChiMucGioHang chiMucGioHang: chiMucGioHangList) {
			ChiTietDonHang chiTietDonHang=new ChiTietDonHang();
			chiTietDonHang.setDonHang(donHang);
			chiTietDonHang.setSanPham(chiMucGioHang.getSanPham());
			sanPhamList.add(this.sanPhamService.getSanPhamByID(chiMucGioHang.getSanPham().getId()));
			chiTietDonHang.setSoLuongDat(chiMucGioHang.getSo_luong());
			chiTietDonHang.setDonGia(chiMucGioHang.getSanPham().getDonGia()*chiMucGioHang.getSo_luong());
			this.chiTietDonHangService.SaveOrUpdate(chiTietDonHang);
			chiTietDonHangList.add(chiTietDonHang);
			
		}
		
		//xoa chi muc gio hang
		for(ChiMucGioHang chiMucGioHang: chiMucGioHangList) {
			this.chiMucGioHangService.remove(chiMucGioHang);
			
		}
		
		model.addAttribute("donHang", donHang);
		model.addAttribute("chiTietDonHangList", chiTietDonHangList);
		model.addAttribute("sanPhamList", sanPhamList);
		
		
		
		return "client/thankyou";
	}
	
	@GetMapping("/test11")
	public String test1(Model model) {
		
		return "client/home";
		
	}
}
