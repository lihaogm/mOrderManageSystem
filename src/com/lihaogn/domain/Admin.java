package com.lihaogn.domain;

public class Admin {

	private String aid;
	private String adminName;
	private String adminPassword;
	private int adminPrivilege;
	private String create_time;

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public int getAdminPrivilege() {
		return adminPrivilege;
	}

	public void setAdminPrivilege(int adminPrivilege) {
		this.adminPrivilege = adminPrivilege;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		
		return "admin[aid: "+aid+"\r\n"+"adminName: "+adminName+"\r\n"+"adminPasseword: "+adminPassword+"\r\n"+"adminPrivilege: "+adminPrivilege+"\r\n"+
				"adminCreateTime: "+create_time+"]";
	}
}
