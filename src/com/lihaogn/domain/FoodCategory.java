package com.lihaogn.domain;

/**
 * 菜品分类
 * @author Mac
 *
 */
public class FoodCategory {

	private String pk_fcwc_id;
	private String fcwc_name;
	private String fcwc_create_time;
	private String fcwc_modified_time;
	
	
	public String getPk_fcwc_id() {
		return pk_fcwc_id;
	}


	public void setPk_fcwc_id(String pk_fcwc_id) {
		this.pk_fcwc_id = pk_fcwc_id;
	}


	public String getFcwc_name() {
		return fcwc_name;
	}


	public void setFcwc_name(String fcwc_name) {
		this.fcwc_name = fcwc_name;
	}


	public String getFcwc_create_time() {
		return fcwc_create_time;
	}


	public void setFcwc_create_time(String fcwc_create_time) {
		this.fcwc_create_time = fcwc_create_time;
	}


	public String getFcwc_modified_time() {
		return fcwc_modified_time;
	}


	public void setFcwc_modified_time(String fcwc_modified_time) {
		this.fcwc_modified_time = fcwc_modified_time;
	}


	@Override
	public String toString() {
	return "food_category[fcid: "+pk_fcwc_id+"\r\n"+"fcName: "+fcwc_name+"\r\n"+"fcCreateDate: "+
			fcwc_create_time+"\r\n"+"fcModifiedDate: "+fcwc_modified_time+"]";
	}
	
}
