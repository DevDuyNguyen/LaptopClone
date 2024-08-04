package com.example.LaptopShopClone.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LaptopShopClone.Entity.DonHang;
import com.example.LaptopShopClone.Repository.DonHangRepository;
import com.example.LaptopShopClone.ServiceInterface.DonHangService;

@Service
public class DonHangServiceImpl implements DonHangService{
	@Autowired 
	DonHangRepository donHangRepository;
	
	public DonHang SaveOrUpdate(DonHang donHang) {
		return this.donHangRepository.saveOrUpdate(donHang);
	}
}
