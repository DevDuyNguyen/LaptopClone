package com.example.LaptopShopClone.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LaptopShopClone.Entity.ChiTietDonHang;
import com.example.LaptopShopClone.Repository.ChiTietDonHangRepository;
import com.example.LaptopShopClone.ServiceInterface.ChiTietDonHangService;

@Service
public class ChiTietDonHangServiceImpl implements ChiTietDonHangService{
	@Autowired
	ChiTietDonHangRepository chiTietDonHangRepository;
	
	public ChiTietDonHang SaveOrUpdate(ChiTietDonHang chiTietDonHang) {
		return this.chiTietDonHangRepository.SaveOrUpdate(chiTietDonHang);
	}
}
