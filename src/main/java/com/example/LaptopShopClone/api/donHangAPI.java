package com.example.LaptopShopClone.api;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.LaptopShopClone.Entity.ChiTietDonHang;
import com.example.LaptopShopClone.Entity.DonHang;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Entity.VaiTro;
import com.example.LaptopShopClone.ServiceInterface.ChiTietDonHangService;
import com.example.LaptopShopClone.ServiceInterface.DonHangService;
import com.example.LaptopShopClone.ServiceInterface.VaiTroService;
import com.example.LaptopShopClone.Utils.ResponseObject;
import com.example.LaptopShopClone.Utils.Validation;
import com.example.LaptopShopClone.Utils.ThongTinChiTietDonHang;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Status;

@RestController
@RequestMapping("/api/donhang")
@SessionAttributes({"loggedUser","role_list"})
public class donHangAPI {
	
	@Autowired
	Validation validation;
	@Autowired
	VaiTroService vaiTroService;
	@Autowired
	DonHangService donHangService;
	@Autowired
	ChiTietDonHangService chiTietDonHangService;
	
	
	@ModelAttribute("loggedUser")
	public NguoiDung getLoggedUser(HttpServletRequest httpServletRequest) {
		return (NguoiDung)httpServletRequest.getSession().getAttribute("loggedUser");
	}
	
	@ModelAttribute("role_list")
	public List<VaiTro> getLoggedUserVaiTro(HttpServletRequest httpServletRequest) {
		NguoiDung ng=getLoggedUser(httpServletRequest);
		if(ng==null)
			return null;
					
		return getLoggedUser(httpServletRequest).getVaiTro();
	}
	
	@GetMapping("/chiTietDonHang")
	public ResponseEntity<ResponseObject> getChiTietDonHang(@RequestParam("id") long id, HttpServletRequest httpServletRequest,
			@ModelAttribute("role_list") List<VaiTro> role_list){
		ResponseObject ro=new ResponseObject();
		if(!validation.isLoggin(httpServletRequest)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		String memberAcess=this.validation.manageRoleAccess(role_list,vaiTroService.getVaiTroByName("ROLE_ADMIN"));
		if(!memberAcess.equals("ok")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		ThongTinChiTietDonHang thongTinChiTietDonHang=new ThongTinChiTietDonHang();
		
		DonHang donHang=this.donHangService.getDonHangById(id);
		
		thongTinChiTietDonHang.setTenNguoiNhan(donHang.getHoTenNguoiNhan());
		thongTinChiTietDonHang.setDiaChiNhan(donHang.getDiaChiNhan());
		thongTinChiTietDonHang.setSdtNguoiNhan(donHang.getSdtNhanHang());
		
		thongTinChiTietDonHang.setTrangThaiDonHang(donHang.getTrangThaiDonHang());
		thongTinChiTietDonHang.setNgayDat(new Date(donHang.getNgayDatHang().getTime()));
		if(donHang.getNgayGiaoHang()!=null)
			thongTinChiTietDonHang.setNgayGiaoHang(new Date(donHang.getNgayGiaoHang().getTime()));
		if(donHang.getNgayNhanHang()!=null)
			thongTinChiTietDonHang.setNgayNhanHang(new Date(donHang.getNgayNhanHang().getTime()));
		thongTinChiTietDonHang.setTongGiaTri(donHang.getTongGiaTri());
		
		thongTinChiTietDonHang.setChiTietDonHangs(this.chiTietDonHangService.getChiTietDonHangFromDonHang(donHang));

		thongTinChiTietDonHang.setTenNguoiDat(donHang.getNguoiDat().getHoTen());
		thongTinChiTietDonHang.setIdNguoiDat(donHang.getNguoiDat().getId());
		
		if(donHang.getShipper()!=null) {
			thongTinChiTietDonHang.setTenShipper(donHang.getShipper().getHoTen());
			thongTinChiTietDonHang.setIdShipper(donHang.getShipper().getId());
		}
		
		
		
		ro.setData(thongTinChiTietDonHang);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(ro);
		
		
	}
	
}
