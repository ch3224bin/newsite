package com.john_na.access.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.john_na.access.service.IAuthService;
import com.john_na.common.api.DaumProfileService;
import com.john_na.common.entity.DaumAPIVo;
import com.john_na.common.entity.DaumProfileVo;

@Service("authService")
public class AuthService implements IAuthService {
	@Inject private AuthDao authDao;
	@Inject private DaumProfileService daumProfileService;
	
	public DaumAPIVo getCalendarAuthKey() throws Exception {
		DaumProfileVo vo = daumProfileService.getProfile();
		DaumAPIVo daumProfileVo = new DaumAPIVo();
		daumProfileVo.setUserId(vo.getId());
		
		DaumAPIVo daumCalendarVo = authDao.findCalendarAuthKeyByPk(daumProfileVo);
		
		return daumCalendarVo;
	}
	
	public void updateProfile(DaumAPIVo daumProfileVo) throws Exception {
		DaumProfileVo vo = daumProfileService.getProfile();
		daumProfileVo.setUserId(vo.getId());
		authDao.updateProfile(daumProfileVo);
	}
	
	public void updateCalendar(DaumAPIVo daumCalendarVo) throws Exception {
		authDao.updateCalendar(daumCalendarVo);
	}

	public void removeCalendarAuthKey() throws Exception {
		DaumProfileVo vo = daumProfileService.getProfile();
		DaumAPIVo daumProfileVo = new DaumAPIVo();
		daumProfileVo.setUserId(vo.getId());
		authDao.removeCalendarAuthKey(daumProfileVo);
	}
}
