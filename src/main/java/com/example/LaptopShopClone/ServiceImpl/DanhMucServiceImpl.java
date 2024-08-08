package com.example.LaptopShopClone.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LaptopShopClone.Entity.DanhMuc;
import com.example.LaptopShopClone.Repository.DanhMucRepository;

@Service
public class DanhMucServiceImpl implements DanhMucService{
	@Autowired
	DanhMucRepository danhMucRepository;
	
	public List<DanhMuc> getAllDanhMuc(){
		return this.danhMucRepository.getAllDanhMuc();
	}
}
