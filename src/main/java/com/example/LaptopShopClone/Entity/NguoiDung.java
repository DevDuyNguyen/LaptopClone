package com.example.LaptopShopClone.Entity;

import java.sql.ResultSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;


@Entity
public class NguoiDung {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	private String password;
	
	@Transient
	private String confirmPassword;
	
	private String hoTen;
	private String soDienThoai;
	private String diaChi;
	
	@ManyToMany
	List<VaiTro> vaiTro;
	
	public NguoiDung(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public NguoiDung() {
		// TODO Auto-generated constructor stub
	}

	
	public List<VaiTro> getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(List<VaiTro> vaiTro) {
		this.vaiTro = vaiTro;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setNguoiDungFromResultSet(ResultSet rs) {
		try {
			this.setId(rs.getLong("id"));
			this.setDiaChi(rs.getString("dia_chi"));
			this.setEmail(rs.getString("email"));
			this.setHoTen(rs.getString("ho_ten"));
			this.setPassword(rs.getString("password"));
		} catch (Exception e) {
			
			System.out.println("cannot convert resultset to nguoidung");
			System.out.println(e.getMessage());
		}
	}

	@Override
	public String toString() {
		return "NguoiDung [id=" + id + ", email=" + email + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", hoTen=" + hoTen + ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi;
	}	
}

