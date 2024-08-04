package com.example.LaptopShopClone.Utils;

import java.util.ArrayList;
import java.util.List;

public class ResponseObject {
	private String status;
	List<String> errors=new ArrayList<String>();
	Object data;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	
}
