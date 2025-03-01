package com.amstech.dairy.management.system.model.request;

public class AddresModelRequest {

	
	    private String countryName;
	    private String stateName;
	    private String cityName;
	    private Integer userId;
	    private String lendMark;
	    private Integer postalCode;
	    private String mapLocation;
	    private String nearBy;
		
	    public String getCountryName() {
			return countryName;
		}
		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}
		public String getStateName() {
			return stateName;
		}
		public void setStateName(String stateName) {
			this.stateName = stateName;
		}
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public String getLendMark() {
			return lendMark;
		}
		public void setLendMark(String lendMark) {
			this.lendMark = lendMark;
		}
		public Integer getPostalCode() {
			return postalCode;
		}
		public void setPostalCode(Integer postalCode) {
			this.postalCode = postalCode;
		}
		public String getMapLocation() {
			return mapLocation;
		}
		public void setMapLocation(String mapLocation) {
			this.mapLocation = mapLocation;
		}
		public String getNearBy() {
			return nearBy;
		}
		public void setNearBy(String nearBy) {
			this.nearBy = nearBy;
		}
	    
	    
}
