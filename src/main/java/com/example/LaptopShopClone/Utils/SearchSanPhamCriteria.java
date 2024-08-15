package com.example.LaptopShopClone.Utils;

import java.math.BigInteger;

public class SearchSanPhamCriteria {
	private String keyWord[];
	private String sort;
	private int minValue;
	private long maxValue;
	private long danhMuc;
	private long hsx;
	private String trangThai;
	private String name;
	
	
	public String[] getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String[] keyWord) {
		this.keyWord = keyWord;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public int getMinValue() {
		return minValue;
	}
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}
	public long getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(long maxValue) {
		this.maxValue = maxValue;
	}
	public long getDanhMuc() {
		return danhMuc;
	}
	public void setDanhMuc(Long danhMuc) {
		this.danhMuc = danhMuc;
	}
//	public String getHsx() {
//		return hsx;
//	}
//	public void setHsx(String hsx) {
//		this.hsx = hsx;
//	}
	
	public String getTrangThai() {
		return trangThai;
	}
	public long getHsx() {
		return hsx;
	}
	public void setHsx(long hsx) {
		this.hsx = hsx;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public void setDanhMuc(long danhMuc) {
		this.danhMuc = danhMuc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
