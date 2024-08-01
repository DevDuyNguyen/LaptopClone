package com.example.LaptopShopClone.Entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class VaiTro {
	@Id
	@GeneratedValue
	private long id;
	private String tenVaiTro;
	
	@ManyToMany(mappedBy = "vaiTro")
	List<NguoiDung> nguoiDungs;
	
	
	public VaiTro() {
		super();
	}
	
	public VaiTro(long id, String tenVaiTro) {
		super();
		this.id = id;
		this.tenVaiTro = tenVaiTro;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTenVaiTro() {
		return tenVaiTro;
	}
	public void setTenVaiTro(String tenVaiTro) {
		this.tenVaiTro = tenVaiTro;
	}
	public List<NguoiDung> getNguoiDungs() {
		return nguoiDungs;
	}
	public void setNguoiDungs(List<NguoiDung> nguoiDungs) {
		this.nguoiDungs = nguoiDungs;
	}
	
	
	
	@Override
	public String toString() {
		return "VaiTro [id=" + id + ", tenVaiTro=" + tenVaiTro + "]";
	}
	

}
