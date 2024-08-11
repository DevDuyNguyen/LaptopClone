package com.example.LaptopShopClone.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.LaptopShopClone.Entity.TrangThaiDonHang;
import com.example.LaptopShopClone.Repository.TrangThaiDonHangRepository;
import com.example.LaptopShopClone.ServiceInterface.TrangThaiDonHangService;

public class TrangThaiDonHangServiceImpl implements TrangThaiDonHangService{
	@Autowired
	TrangThaiDonHangRepository trangThaiDonHangRepository;
	
	public TrangThaiDonHang getTrangThaiDonHangByName(String name) {
		return this.trangThaiDonHangRepository.getTrangThaiDonHangByName(name);
	}
}
