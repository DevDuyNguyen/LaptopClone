package com.example.LaptopShopClone.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LaptopShopClone.Entity.ChiMucGioHang;
import com.example.LaptopShopClone.Entity.GioHang;
import com.example.LaptopShopClone.Entity.SanPham;
import com.example.LaptopShopClone.Repository.ChiMucGioHangRepository;
import com.example.LaptopShopClone.ServiceInterface.ChiMucGioHangService;

@Service
public class ChiMucGioHangServiceImpl implements ChiMucGioHangService{
	@Autowired
	ChiMucGioHangRepository chiMucGioHangRepository;
	
	public ChiMucGioHang getChiMucGioHangBySanPhangGioHang(SanPham sanPham, GioHang gioHang) {
		return this.chiMucGioHangRepository.getChiMucGioHangBySanPhangGioHang(sanPham, gioHang);
	}
	
	public ChiMucGioHang SaveOrUpdate(ChiMucGioHang chiMucGioHang) {
		return this.chiMucGioHangRepository.save(chiMucGioHang);
	}
	public void remove(ChiMucGioHang chiMucGioHang) {
		this.chiMucGioHangRepository.remove(chiMucGioHang);
	}
	
}
