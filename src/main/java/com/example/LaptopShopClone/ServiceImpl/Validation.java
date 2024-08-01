package com.example.LaptopShopClone.ServiceImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.LaptopShopClone.Repository.NguoiDungRepository;

@Service
public class Validation {
	
	NguoiDungServiceImpl nguoiDungServiceImpl;
	
	@Autowired
	public Validation(NguoiDungServiceImpl nguoiDungServiceImpl) {
		this.nguoiDungServiceImpl=nguoiDungServiceImpl;
		
	}
	
	public String isEmailFormat(String email) {
		Pattern pattern=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher=pattern.matcher(email);
		if(!matcher.matches()) {
			return "Địa chỉ email không phù hợp";
		}
		
		return null;
		
	}
	
	public String isEmailAlreadyUsed(String email) {
		if(this.nguoiDungServiceImpl.findByEmail(email)!=null) {
			return "Địa chỉ email đã được sử dụng";
		}
		return null;
	}
	
	
	
 
	
	
	
}
