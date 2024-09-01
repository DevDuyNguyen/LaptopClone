package com.example.LaptopShopClone.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LaptopShopClone.Entity.DanhMuc;
import com.example.LaptopShopClone.Repository.DanhMucRepository;
import com.example.LaptopShopClone.ServiceInterface.DanhMucService;

@Service
public class DanhMucServiceImpl implements DanhMucService{
	@Autowired
	DanhMucRepository danhMucRepository;
	
	public DanhMucServiceImpl() {
		System.out.println("DanhMucServiceImpl is created");
	}
	
	public List<DanhMuc> getAllDanhMuc(){
		return this.danhMucRepository.getAllDanhMuc();
	}
	
	public DanhMuc getDanhMucById(long id) {
		return this.danhMucRepository.getDanhMucById(id);
	}
}
