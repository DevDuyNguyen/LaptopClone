package com.example.LaptopShopClone.Utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;

import com.example.LaptopShopClone.Entity.DanhMuc;
import com.example.LaptopShopClone.Entity.HangSanXuat;
import com.example.LaptopShopClone.ServiceInterface.DanhMucService;
import com.example.LaptopShopClone.ServiceInterface.HangSanXuatService;

@Component
@RequestScope
public class ErrorsContainer<KeyType> {
	@Autowired
	DanhMucService danhMucService;
	@Autowired
	HangSanXuatService hangSanXuatService;
	
	
	private Map<KeyType, List<String>> errors=new HashMap<KeyType, List<String>>();
	private boolean hasError=false;
	
	private void initializeErrorKey(KeyType errorKey) {
		if(errors.containsKey(errorKey))
			return;
		errors.put(errorKey, new LinkedList<String>());
	}
	
	public boolean isEmptyOrNullString(String str, KeyType errorKey) {
		if(str==null || str.equals("")) {
			initializeErrorKey(errorKey);
			this.errors.get(errorKey).add("Không được để trống");
			this.hasError=true;
			return false;
			
		}
		return true;
	}
	
	public boolean isNumberic(String number, KeyType errorKey) {
		try {
			Double.parseDouble(number);
		} catch (NumberFormatException e) {
			initializeErrorKey(errorKey);
			this.errors.get(errorKey).add("Không phải là số");
			this.hasError=true;
			return false;
		}
		return true;
	}
	
	public boolean isInteger(String number, KeyType errorKey) {
		try {
			Integer.parseInt(number);
		} catch (NumberFormatException e) {
			initializeErrorKey(errorKey);
			this.errors.get(errorKey).add("Không phải số nguyên");
			this.hasError=true;
			return false;
		}
		return true;
	}
	
	public boolean isPositiveNumber(String number, KeyType errorKey) {
		if(isNumberic(number, errorKey)) {
			if(Double.parseDouble(number)>0)
				return true;
		}
		this.hasError=true;
		initializeErrorKey(errorKey);
		errors.get(errorKey).add("Không phải số dương");
		return false;
	}
	
	public boolean isDanhMucValid(String danhMuc, KeyType errorKey) {
		DanhMuc danhmuc=this.danhMucService.getDanhMucById(Long.parseLong(danhMuc));
		if(danhmuc==null) {
			initializeErrorKey(errorKey);
			errors.get("danhMuc").add("Danh mục không tồn tại");
			this.hasError=true;
			
			return false;
		}
		return true;
		
	}
	
	public boolean isHSXValid(String hangSanXuat, KeyType errorKey) {
		HangSanXuat hsx=this.hangSanXuatService.getHSXById(Long.parseLong(hangSanXuat));
		if(hsx==null) {
			initializeErrorKey(errorKey);
			errors.get("hangSanXuat").add("Hãng sản xuất không tồn tại");
			this.hasError=true;
			
			return false;
		}
		return true;
	}
	
	public boolean isMultipartFileEmpty(MultipartFile file, KeyType errorKey) {
		if(file.isEmpty()) {
			initializeErrorKey(errorKey);
			errors.get(errorKey).add("File không được để trống");
			this.hasError=true;
			return false;
			
		}
		return true;
	}

	public Map<KeyType, List<String>> getErrors() {
		return errors;
	}

	public boolean isHasError() {
		return hasError;
	}
	
	
	
	
	
}
