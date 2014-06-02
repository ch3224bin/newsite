package com.john_na.category.service.impl;

import java.util.Collection;

import javax.inject.Inject;

import org.anyframe.query.QueryService;
import org.anyframe.query.dao.AbstractDao;
import org.springframework.stereotype.Repository;

import com.john_na.common.entity.DaumCalendarManageVo;

@Repository
public class CategoryDao extends AbstractDao {
	
	@Inject
	public void setQueryService(QueryService queryService) {
		super.setQueryService(queryService);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<DaumCalendarManageVo> findDaumCalendarManageList(String userId) throws Exception {
		DaumCalendarManageVo vo = new DaumCalendarManageVo();
		vo.setUserId(userId);
		return findList("DaumCalendarManage", vo);
	}

	public void updateDaumCalendarManage(DaumCalendarManageVo vo) throws Exception {
		update("DaumCalendarManage", vo);
	}
	
	public void removeDaumCalendarManage(DaumCalendarManageVo vo) throws Exception {
		remove("DaumCalendarManage", vo);
	}

}
