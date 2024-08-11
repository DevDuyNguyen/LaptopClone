package com.example.LaptopShopClone.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TrangThaiDonHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String tenTrangThai;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTenTrangThai() {
		return tenTrangThai;
	}
	public void setTenTrangThai(String tenTrangThai) {
		this.tenTrangThai = tenTrangThai;
	}
	@Override
	public String toString() {
		return "TrangThaiDonHang [id=" + id + ", tenTrangThai=" + tenTrangThai + "]";
	}
	
	
	
}
