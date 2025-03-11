package com.amstech.dairy.management.system.responce;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RestResponce {

	
	Map<String, Object> responseMap;
	
	public RestResponce () {
		this.responseMap = new HashMap<String ,Object>();
	}
	
	public static RestResponce build() {
		return new RestResponce() ;
		
	}
	
	private void timestamp() {
		this.responseMap.put("timestamp", new Date().getTime());
	}
	
	public RestResponce data(Object data) {
		this.responseMap.put("data", data);
		return this;
	}
	public RestResponce statusName(String statusName) {
		this.responseMap.put("statusName", statusName);
		return this;
	}
	public RestResponce statusCode(int statusCode) {
		this.responseMap.put("statusCode", statusCode);
		return this;
	}
	
	public RestResponce success(int successCode) {
		this.statusCode(successCode);
		this.statusName("sucess");
		return this;
	}
	
	public RestResponce error(int errorCode) {
		this.statusCode(errorCode);
		this.statusName("error");
		return this;
	}
	public RestResponce page(long page) {
		this.responseMap.put("page", page);
		return this;
	}
	public RestResponce size(long size) {
		this.responseMap.put("size", size);
		return this;
		
	}
	public RestResponce totalRecord(long totalRecord ) {
		this.responseMap.put("totalRecord", totalRecord);
		return this;
	}
	
	public RestResponce message(String message ) {
		this.responseMap.put("message", message);
		return this;
	
}
	
	 public Map<String, Object> getResponse() {
	        return this.responseMap;
	    }
}
