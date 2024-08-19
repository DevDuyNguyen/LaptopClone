package com.example.LaptopShopClone.Utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Entity.VaiTro;
import com.example.LaptopShopClone.ServiceImpl.NguoiDungServiceImpl;
import com.example.LaptopShopClone.ServiceInterface.VaiTroService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Service
public class Validation {
	
	@Autowired 
	VaiTroService vaiTroService;
	
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
	
	public String manageRoleAccess(List<VaiTro> role_list, VaiTro pageScope) {
		if(pageScope.getTenVaiTro().equals("ROLE_MEMBER"))
			return "ok";
		else {
			for(VaiTro vaitro:role_list) {
				if(vaitro.getTenVaiTro().equals(pageScope.getTenVaiTro()))
					return "ok";
			}
		}
		
		return "/home";
		
	}
	
	public Boolean isLoggin(HttpServletRequest httpServletRequest) {
		HttpSession session=httpServletRequest.getSession();
		NguoiDung nguoiDung=(NguoiDung)session.getAttribute("loggedUser");
		return nguoiDung!=null;
	}
	
	public boolean isAdmin(NguoiDung loggedUser, List<VaiTro> role_list) {
		if(loggedUser==null || role_list==null)
			return false;
		for(VaiTro role: role_list) {
			if(role.getTenVaiTro().equals("ROLE_ADMIN"))
				return true;
		}
		
		return false;
	}
	
}
