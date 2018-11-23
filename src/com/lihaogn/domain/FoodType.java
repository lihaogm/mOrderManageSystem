package com.lihaogn.domain;

public class FoodType {

	private int pk_ftc_id;
	private String ftc_name;
	private String ftc_create_time;
	private String ftc_modified_time;
	
	public int getPk_ftc_id() {
		return pk_ftc_id;
	}
	public void setPk_ftc_id(int pk_ftc_id) {
		this.pk_ftc_id = pk_ftc_id;
	}
	public String getFtc_name() {
		return ftc_name;
	}
	public void setFtc_name(String ftc_name) {
		this.ftc_name = ftc_name;
	}
	public String getFtc_create_time() {
		return ftc_create_time;
	}
	public void setFtc_create_time(String ftc_create_time) {
		this.ftc_create_time = ftc_create_time;
	}
	public String getFtc_modified_time() {
		return ftc_modified_time;
	}
	public void setFtc_modified_time(String ftc_modified_time) {
		this.ftc_modified_time = ftc_modified_time;
	}
	
	@Override
	public String toString() {
		return "food_type[pk_ftc_id: "+pk_ftc_id+"\r\n"+"ftc_name: "+ftc_name+"\r\n"+"ftc_create_time: "+
				ftc_create_time+"\r\n"+"fcModifiedDate: "+ftc_modified_time+"]";
	}
	
}
