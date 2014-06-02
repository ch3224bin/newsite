package com.john_na.common.entity;

import java.util.Collection;

public class DailyStatisticsVo extends DaumCalendarEventVo {
	private int min;
	private int count;
	private String startTime;
	private String endTime;
	private String categoryName;
	private Collection<DailyStatisticsVo> events;
	
	public DailyStatisticsVo() {}
	public DailyStatisticsVo(DaumCalendarEventVo event) {
		this.setId(event.getId());
		this.setUid(event.getUid());
		this.setCategoryId(event.getCategoryId());
		this.setTitle(event.getTitle());
		this.setDescription(event.getDescription());
		this.setStartAt(event.getStartAt());
		this.setEndAt(event.getEndAt());
		this.setLocation(event.getLocation());
		this.setAllday(event.getAllday());
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public Collection<DailyStatisticsVo> getEvents() {
		return events;
	}
	public void setEvents(Collection<DailyStatisticsVo> events) {
		this.events = events;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
