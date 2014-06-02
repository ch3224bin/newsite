package com.john_na.access.service.impl;

import javax.inject.Inject;

import org.anyframe.query.QueryService;
import org.anyframe.query.dao.AbstractDao;
import org.springframework.stereotype.Repository;

import com.john_na.common.entity.DaumAPIVo;

@Repository("authDao")
public class AuthDao extends AbstractDao {
	@Inject
	public void setQueryService(QueryService queryService) {
		super.setQueryService(queryService);
	}

	public DaumAPIVo findCalendarAuthKeyByPk(DaumAPIVo daumProfileVo) throws Exception {
		return (DaumAPIVo) findByPk("CalendarAuthKey", daumProfileVo);
	}
	
	public void updateProfile(DaumAPIVo daumProfileVo) throws Exception {
		update("Profile", daumProfileVo);
	}
	
	public void updateCalendar(DaumAPIVo daumCalendarVo) throws Exception {
		update("Calendar", daumCalendarVo);
	}

	public void removeCalendarAuthKey(DaumAPIVo daumProfileVo) throws Exception {
		remove("CalendarAuthKey", daumProfileVo);
	}
}
