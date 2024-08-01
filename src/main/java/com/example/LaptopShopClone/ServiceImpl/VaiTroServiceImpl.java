package com.example.LaptopShopClone.ServiceImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LaptopShopClone.Entity.VaiTro;
import com.example.LaptopShopClone.Repository.VaiTroRepository;
import com.example.LaptopShopClone.ServiceInterface.VaiTroService;
import com.example.LaptopShopClone.Utils.SessionFactoryUtil;

@Service
public class VaiTroServiceImpl implements VaiTroService{
	VaiTroRepository vaiTroRepository;
	
	@Autowired
	public VaiTroServiceImpl(VaiTroRepository vaiTroRepository) {
		this.vaiTroRepository=vaiTroRepository;
	}
	
	public VaiTro getVaiTroByName(String name) {
		return this.vaiTroRepository.getVaiTroByName(name);
		
		
	}
	public void addVaiTro(VaiTro vaiTro) {
		
	}
	
}
