package com.john_na.common.entity;

import com.google.gson.annotations.SerializedName;

public class DaumCalendarEventVo {
//	{
//		"completed_at":null,
//		"location":null,
//		"title":"api_event_test",
//		"end_at":1292252400000,
//		"url":"http://calendar.daum.net",
//		"id":3179,
//		"start_at":1292252400000,
//		"uid":"3520api_test@daum",
//		"attach":"",
//		"repeat":{"rrule":"FREQ=YEARLY;","id":28,"exdates": "20111214"},
//		"category_id":2,
//		"description":"",
//		"allday":true
//	}
	private String id;
	private String uid;
	@SerializedName("category_id")
	private String categoryId;
	private String title;
	private String description;
	private String location;
	@SerializedName("start_at")
	private String startAt;
	@SerializedName("end_at")
	private String endAt;
	private boolean allday = false;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStartAt() {
		return startAt;
	}
	public void setStartAt(String startAt) {
		this.startAt = startAt;
	}
	public String getEndAt() {
		return endAt;
	}
	public void setEndAt(String endAt) {
		this.endAt = endAt;
	}
	public boolean getAllday() {
		return allday;
	}
	public void setAllday(boolean allday) {
		this.allday = allday;
	}
}
