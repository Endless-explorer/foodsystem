package com.jmy.model;

public class Location {
    private String timezone;
    private long  timezone_offset_gmt;
    private float latitude;  //维度
    private float longitude; //经度
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public long getTimezone_offset_gmt() {
		return timezone_offset_gmt;
	}
	public void setTimezone_offset_gmt(long timezone_offset_gmt) {
		this.timezone_offset_gmt = timezone_offset_gmt;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
}
