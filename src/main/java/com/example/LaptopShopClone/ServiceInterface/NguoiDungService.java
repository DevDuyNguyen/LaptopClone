package com.example.LaptopShopClone.ServiceInterface;

import com.example.LaptopShopClone.Entity.NguoiDung;

public interface NguoiDungService {
	NguoiDung findByEmail(String email);
	
	NguoiDung saveUserForMember(NguoiDung nd);// nho xet vai tro la member
	
	public NguoiDung SaveOrUpdate(NguoiDung nguoiDung);
	
}
