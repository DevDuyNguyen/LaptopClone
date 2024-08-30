package com.example.LaptopShopClone.ServiceInterface;

import java.util.List;

import com.example.LaptopShopClone.Entity.DanhMuc;

public interface DanhMucService {
	List<DanhMuc> getAllDanhMuc();
	public DanhMuc getDanhMucById(long id);
}
