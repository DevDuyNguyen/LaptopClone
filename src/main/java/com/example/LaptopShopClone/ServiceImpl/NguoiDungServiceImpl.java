package com.example.LaptopShopClone.ServiceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Entity.VaiTro;
import com.example.LaptopShopClone.Repository.NguoiDungRepository;
import com.example.LaptopShopClone.Repository.VaiTroRepository;
import com.example.LaptopShopClone.ServiceInterface.NguoiDungService;

@Service
public class NguoiDungServiceImpl implements NguoiDungService {
	@Autowired NguoiDungRepository nguoiDungRepository;
	@Autowired VaiTroRepository vaiTroRepository;
	
	
	public NguoiDung findByEmail(String email) {
		return this.nguoiDungRepository.getNguoiDungByEmail(email);
	}
	
	public NguoiDung saveUserForMember(NguoiDung nguoiDung) {
		nguoiDung.setVaiTro(new ArrayList<VaiTro>());
		nguoiDung.getVaiTro().add(vaiTroRepository.getVaiTroByName("ROLE_MEMBER"));
		return this.nguoiDungRepository.addNguoiDung(nguoiDung);
	}
	
	public NguoiDung SaveOrUpdate(NguoiDung nguoiDung) {
		return this.nguoiDungRepository.SaveOrUpdate(nguoiDung);
	}
	
}
