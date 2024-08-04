package com.example.LaptopShopClone.api;

import java.util.function.Predicate;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.LaptopShopClone.Entity.ChiMucGioHang;
import com.example.LaptopShopClone.Entity.GioHang;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Entity.SanPham;
import com.example.LaptopShopClone.ServiceInterface.ChiMucGioHangService;
import com.example.LaptopShopClone.ServiceInterface.GioHangService;
import com.example.LaptopShopClone.ServiceInterface.SanPhamService;
import com.example.LaptopShopClone.Utils.ResponseObject;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/gio-hang")
@SessionAttributes({"loggedUser"})


public class cartAPI {
	@Autowired
	GioHangService gioHangService;
	@Autowired
	SanPhamService sanPhamService;
	@Autowired
	ChiMucGioHangService chiMucGioHangService;
	
	@ModelAttribute("loggedUser")
	public NguoiDung getLoggedUser(HttpServletRequest httpServletRequest) {
		return (NguoiDung)httpServletRequest.getSession().getAttribute("loggedUser");
	}
	
	static public NguoiDung getLoggedInUser(HttpServletRequest httpServletRequest) {
		return (NguoiDung)httpServletRequest.getSession().getAttribute("loggedUser");
	}
	@GetMapping("/test")
	public void test(@RequestParam("sp_id") long sp_id) {
		System.out.println("test is called");
	}
	
	@GetMapping("/addToCart")
	public ResponseObject addToCart(@RequestParam("sp_id") long sp_id, HttpServletRequest httpServletRequest) {
		System.out.println("addToCart is requested");
		ResponseObject ro=new ResponseObject();
		NguoiDung nguoiDung=cartAPI.getLoggedInUser(httpServletRequest);
		if(nguoiDung==null) {
			ro.getErrors().add("Tài khoản chưa đăng nhập");
			ro.setStatus("fail");
			return ro;
		}
		
		GioHang gioHang=this.gioHangService.getGioHangByNguoiDung(nguoiDung);
		if(gioHang==null) {
			System.out.println("giohang is null");
			this.gioHangService.addGioHangForNguoiDung(nguoiDung);
			gioHang=this.gioHangService.getGioHangByNguoiDung(nguoiDung);
		}
		SanPham sanPham=this.sanPhamService.getSanPhamByID(sp_id);
		if(sanPham.getDonViKho()==0) {
			ro.setStatus("fail");
			ro.getErrors().add("Sản phẩm đang hết hàng, quý khách vui lòng quay lại sau");
			return ro;
		}

		
		ChiMucGioHang chiMucGioHang=this.chiMucGioHangService.getChiMucGioHangBySanPhangGioHang(sanPham, gioHang);
		System.out.println("HERE");
		if(chiMucGioHang==null)//chua them san pham vao gio hang tu truoc
		{
			chiMucGioHang=new ChiMucGioHang();
			chiMucGioHang.setGioHang(gioHang);
			chiMucGioHang.setSanPham(sanPham);
			chiMucGioHang.setSo_luong(1);
			gioHang.getChiMucGioHangs().add(chiMucGioHang);

			this.chiMucGioHangService.SaveOrUpdate(chiMucGioHang);		
			this.gioHangService.updateGiohang(gioHang);
		}
		else {//da them san pham tu truoc
			chiMucGioHang.setSo_luong(chiMucGioHang.getSo_luong()+1);
			this.chiMucGioHangService.SaveOrUpdate(chiMucGioHang);
		}
		
		ro.setStatus("success");
		
		return ro;
	}
	
	@GetMapping("/changeQuantity")//sp_id="+sp_id+"&soLuong=
	public ResponseObject changeQuentity(HttpServletRequest httpServletRequest, @RequestParam("sp_id") long sp_id, @RequestParam("soLuong") int soLuong) {
		System.out.println("changeQuantity is requested");
		ResponseObject ro=new ResponseObject();
		NguoiDung nguoiDung=this.getLoggedInUser(httpServletRequest);
		if(nguoiDung==null) {
			ro.getErrors().add("Vui lòng đăng nhập");
			ro.setStatus("fail");
			return ro;
		}
		if(soLuong<0) {
			ro.getErrors().add("Số lượng không hợp lệ");
			ro.setStatus("fail");
			return ro;
		}
		GioHang gioHang=this.gioHangService.getGioHangByNguoiDung(nguoiDung);
		if(gioHang==null) {
			ro.getErrors().add("Sản phẩm chưa có trong giỏ hàng");
			ro.setStatus("fail");
			return ro;
		}
		
		SanPham sanPham=this.sanPhamService.getSanPhamByID(sp_id);
		if(sanPham==null) {
			ro.getErrors().add("Sản phẩm không tồn tại");
			ro.setStatus("fail");
			return ro;
		}
		
		ChiMucGioHang chiMucGioHang=this.chiMucGioHangService.getChiMucGioHangBySanPhangGioHang(sanPham, gioHang);
		if(chiMucGioHang==null) {
			ro.getErrors().add("Sản phẩm chưa có trong giỏ hàng");
			ro.setStatus("fail");
			return ro;
		}
		chiMucGioHang.setSo_luong(soLuong);
		this.chiMucGioHangService.SaveOrUpdate(chiMucGioHang);
		
		ro.setStatus("success");
		
		return ro;
	}
	
	@GetMapping("/deleteSanPham")
	public ResponseObject deleteSanPham(@RequestParam("sp_id") long sp_id, HttpServletRequest httpServletRequest) {
		ResponseObject ro=new ResponseObject();
		NguoiDung nguoiDung=this.getLoggedInUser(httpServletRequest);
		if(nguoiDung==null) {
			ro.getErrors().add("Vui lòng đăng nhập");
			ro.setStatus("fail");
			return ro;
		}
		GioHang gioHang=this.gioHangService.getGioHangByNguoiDung(nguoiDung);
		if(gioHang==null) {
			ro.getErrors().add("Sản phẩm chưa có trong giỏ hàng");
			ro.setStatus("fail");
			return ro;
		}
		SanPham sanPham=this.sanPhamService.getSanPhamByID(sp_id);
		if(sanPham==null) {
			ro.getErrors().add("Sản phẩm không tồn tại");
			ro.setStatus("fail");
			return ro;
		}
		
		ChiMucGioHang chiMucGioHang=this.chiMucGioHangService.getChiMucGioHangBySanPhangGioHang(sanPham, gioHang);
		if(chiMucGioHang==null) {
			ro.getErrors().add("Sản phẩm chưa có trong giỏ hàng");
			ro.setStatus("fail");
			return ro;
		}

		
		Predicate<ChiMucGioHang> predicate=chiMucGioHang1->(chiMucGioHang1.getId()==chiMucGioHang.getId());
		
		
		gioHang.getChiMucGioHangs().removeIf(predicate);
		this.chiMucGioHangService.remove(chiMucGioHang);
		
		
		ro.setStatus("success");
		
		return ro;
	}
	
}
