package com.lihaogn.domain;

public class Food {
	
	private String pk_fid;
	private String fname;
	private double fmarket_price;
	private double fshop_price;
	private String fimage;
	private String fcreat_date;
	private String fmodified_date;
	private short fis_hot;
	private String fdesc;
	private short fis_onsale;
	private String fcwc_id;
	private int ftc_id;
	private long food_sales_num;
//	private FoodCategory foodCategory;
//	private FoodType foodType;
	
	public long getFood_sales_num() {
		return food_sales_num;
	}
	public void setFood_sales_num(long food_sales_num) {
		this.food_sales_num = food_sales_num;
	}
	public String getPk_fid() {
		return pk_fid;
	}
	public void setPk_fid(String pk_fid) {
		this.pk_fid = pk_fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public double getFmarket_price() {
		return fmarket_price;
	}
	public void setFmarket_price(double fmarket_price) {
		this.fmarket_price = fmarket_price;
	}
	public double getFshop_price() {
		return fshop_price;
	}
	public void setFshop_price(double fshop_price) {
		this.fshop_price = fshop_price;
	}
	public String getFimage() {
		return fimage;
	}
	public void setFimage(String fimage) {
		this.fimage = fimage;
	}
	public String getFcreat_date() {
		return fcreat_date;
	}
	public void setFcreat_date(String fcreat_date) {
		this.fcreat_date = fcreat_date;
	}
	public String getFmodified_date() {
		return fmodified_date;
	}
	public void setFmodified_date(String fmodified_date) {
		this.fmodified_date = fmodified_date;
	}
	public short getFis_hot() {
		return fis_hot;
	}
	public void setFis_hot(short fis_hot) {
		this.fis_hot = fis_hot;
	}
	public String getFdesc() {
		return fdesc;
	}
	public void setFdesc(String fdesc) {
		this.fdesc = fdesc;
	}
	public short getFis_onsale() {
		return fis_onsale;
	}
	public void setFis_onsale(short fis_onsale) {
		this.fis_onsale = fis_onsale;
	}
	public String getFcwc_id() {
		return fcwc_id;
	}
	public void setFcwc_id(String fcwc_id) {
		this.fcwc_id = fcwc_id;
	}
	public int getFtc_id() {
		return ftc_id;
	}
	public void setFtc_id(int ftc_id) {
		this.ftc_id = ftc_id;
	}
//	public FoodCategory getFoodCategory() {
//		return foodCategory;
//	}
//	public void setFoodCategory(FoodCategory foodCategory) {
//		this.foodCategory = foodCategory;
//	}
//	public FoodType getFoodType() {
//		return foodType;
//	}
//	public void setFoodType(FoodType foodType) {
//		this.foodType = foodType;
//	}
	
	/*
	 * private String pk_fid;
	private String fname;
	private double fmarket_price;
	private double fshop_price;
	private String fimage;
	private String fcreat_date;
	private String fmodified_date;
	private short fis_hot;
	private String fdesc;
	private short fis_onsale;
	private String fcwc_id;
	private int ftc_id;
	 */
	
	@Override
	public String toString() {
		
		return "food[pk_fid: "+pk_fid+"\r\n"+"fname: "+fname+"\r\n"+
				"fmarket_price: "+fmarket_price+"\r\n"+"fshop_price: "+fshop_price+"\r\n"+
				"fimage:"+fimage+"\r\n"+"fcreat_date: "+fcreat_date+"\r\n"+
				"fmodified_date: "+fmodified_date+"\r\n"+"fis_hot: "+fis_hot+"\r\n"+
				"fdesc: "+fdesc+"\r\n"+"fis_onsale: "+fis_onsale+"\r\n"+
				"fcwc_id: "+fcwc_id+"\r\n"+"ftc_id: "+ftc_id+"]";
	}
	
//	@Override
//	public String toString() {
//		
//		return "food[pk_fid: "+pk_fid+"\r\n"+"fname: "+fname+"\r\n"+
//				"fmarket_price: "+fmarket_price+"\r\n"+"fshop_price: "+fshop_price+"\r\n"+
//				"fimage:"+fimage+"\r\n"+"fcreat_date: "+fcreat_date+"\r\n"+
//				"fmodified_date: "+fmodified_date+"\r\n"+"fis_hot: "+fis_hot+"\r\n"+
//				"fdesc: "+fdesc+"\r\n"+"fis_onsale: "+fis_onsale+"\r\n"+
//				"fcwc: "+foodCategory.toString()+"\r\n"+"ftc: "+foodType.toString()+"]";
//	}
//	
	

}
