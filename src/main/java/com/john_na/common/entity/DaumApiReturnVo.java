package com.john_na.common.entity;

public class DaumApiReturnVo {
	private String status;
	private String resultMsg;
	private DaumProfileVo user;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public DaumProfileVo getUser() {
		return user;
	}
	public void setUser(DaumProfileVo user) {
		this.user = user;
	}
}
