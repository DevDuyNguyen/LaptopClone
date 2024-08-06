package com.example.LaptopShopClone.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LaptopShopClone.Entity.ChiTietDonHang;
import com.example.LaptopShopClone.Entity.DonHang;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Repository.ChiTietDonHangRepository;
import com.example.LaptopShopClone.ServiceInterface.ChiTietDonHangService;

@Service
public class ChiTietDonHangServiceImpl implements ChiTietDonHangService{
	@Autowired
	ChiTietDonHangRepository chiTietDonHangRepository;
	
	public ChiTietDonHang SaveOrUpdate(ChiTietDonHang chiTietDonHang) {
		return this.chiTietDonHangRepository.SaveOrUpdate(chiTietDonHang);
	}
	
	public List<ChiTietDonHang> getChiTietDonHangFromDonHang(DonHang donHang) {
		return this.chiTietDonHangRepository.getChiTietDonHangFromDonHang(donHang);
		
	}
	
}
