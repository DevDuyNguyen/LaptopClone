package com.example.LaptopShopClone.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ChiTietDonHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="ma_san_pham")
	private SanPham sanPham;
	private long donGia;
	private int soLuongDat;
	
	private int soLuongNhanHang;

	@ManyToOne
	@JoinColumn(name = "ma_don_hang")
	private DonHang donHang;
	
	

	public ChiTietDonHang() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public long getDonGia() {
		return donGia;
	}

	public void setDonGia(long donGia) {
		this.donGia = donGia;
	}

	public int getSoLuongDat() {
		return soLuongDat;
	}

	public void setSoLuongDat(int soLuongDat) {
		this.soLuongDat = soLuongDat;
	}

	public int getSoLuongNhanHang() {
		return soLuongNhanHang;
	}

	public void setSoLuongNhanHang(int soLuongNhanHang) {
		this.soLuongNhanHang = soLuongNhanHang;
	}

	public DonHang getDonHang() {
		return donHang;
	}

	public void setDonHang(DonHang donHang) {
		this.donHang = donHang;
	}

	@Override
	public String toString() {
		return "ChiTietDonHang [id=" + id + ", donGia=" + donGia + ", soLuongDat=" + soLuongDat + ", soLuongNhanHang="
				+ soLuongNhanHang + "]";
	}
	
	
	
	
}
