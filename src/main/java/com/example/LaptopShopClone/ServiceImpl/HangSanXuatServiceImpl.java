package com.example.LaptopShopClone.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LaptopShopClone.Entity.HangSanXuat;
import com.example.LaptopShopClone.Repository.HangSanXuatRepository;
import com.example.LaptopShopClone.ServiceInterface.HangSanXuatService;

@Service
public class HangSanXuatServiceImpl implements HangSanXuatService{
	@Autowired
	HangSanXuatRepository hangSanXuatRepository;
	
	public List<HangSanXuat> getAllHSX(){
		return this.hangSanXuatRepository.getAllHSX();
	}
}
