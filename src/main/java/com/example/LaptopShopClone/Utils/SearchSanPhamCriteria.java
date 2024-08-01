package com.example.LaptopShopClone.Utils;

import java.math.BigInteger;

public class SearchSanPhamCriteria {
	private String keyWord[];
	private String sort;
	private int minValue;
	private long maxValue;
	private String danhMuc;
	private String hsx;
	
	
	
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
	public String getDanhMuc() {
		return danhMuc;
	}
	public void setDanhMuc(String danhMuc) {
		this.danhMuc = danhMuc;
	}
	public String getHsx() {
		return hsx;
	}
	public void setHsx(String hsx) {
		this.hsx = hsx;
	}
	
	
	
}
