package com.john_na.access.service;

import com.john_na.common.entity.DaumAPIVo;

public interface IAuthService {
	public DaumAPIVo getCalendarAuthKey() throws Exception;
	public void updateProfile(DaumAPIVo daumProfileVo) throws Exception;
	public void updateCalendar(DaumAPIVo daumCalendarVo) throws Exception;
	public void removeCalendarAuthKey() throws Exception;
}
