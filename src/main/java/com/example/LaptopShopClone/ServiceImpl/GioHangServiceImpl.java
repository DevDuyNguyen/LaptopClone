package com.example.LaptopShopClone.ServiceImpl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LaptopShopClone.Entity.GioHang;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Repository.gioHangRepository;
import com.example.LaptopShopClone.ServiceInterface.GioHangService;

@Service
public class GioHangServiceImpl implements GioHangService{
	@Autowired 
	gioHangRepository gioHangRepository;
	
	public GioHang getGioHangByNguoiDung(NguoiDung nguoiDung) {
		return this.gioHangRepository.getGioHangByNguoiDung(nguoiDung);
	}
	
	public GioHang addGioHangForNguoiDung(NguoiDung nguoiDung) {
		GioHang gioHang=new GioHang();
		gioHang.setNguoiDung(nguoiDung);
		return this.gioHangRepository.save(gioHang);
	}
	
	public void updateGiohang(GioHang gioHang) {
		this.gioHangRepository.save(gioHang);
	}
	public GioHang saveOrUpdate(GioHang gioHang) {
		return this.gioHangRepository.save(gioHang);
	}
}
