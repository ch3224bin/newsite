package com.john_na.main.service.impl;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.john_na.common.api.DaumCalendarService;
import com.john_na.common.entity.DaumAPIVo;
import com.john_na.common.entity.DaumCalendarEventVo;
import com.john_na.common.entity.RecordInProgressVo;
import com.john_na.common.util.ThreadLocalUtil;
import com.john_na.main.service.IMainService;

@Transactional(rollbackFor = Exception.class)
@Service
public class MainService implements IMainService{

	@Inject private MainDao mainDao;
	@Inject private DaumCalendarService daumCalendarService;
	
	public RecordInProgressVo getRecordInProgress() throws Exception {
		DaumAPIVo daumProfileApiVo = (DaumAPIVo) ThreadLocalUtil.get("daumProfileApiVo");
		
		RecordInProgressVo vo = new RecordInProgressVo();
		vo.setUserId(daumProfileApiVo.getUserId());
		
		return mainDao.findRecordInProgressByPk(vo);
	}
	
	public String start(RecordInProgressVo vo) throws Exception {
		
		DaumAPIVo daumProfileApiVo = (DaumAPIVo) ThreadLocalUtil.get("daumProfileApiVo");
		vo.setUserId(daumProfileApiVo.getUserId());
		
		if (mainDao.findRecordInProgressByPk(vo) == null) {
			mainDao.createRecordInProgress(vo);
		}
		
		return "";
	}
	
	public String update(RecordInProgressVo vo) throws Exception {
		
		DaumAPIVo daumProfileApiVo = (DaumAPIVo) ThreadLocalUtil.get("daumProfileApiVo");
		vo.setUserId(daumProfileApiVo.getUserId());
		
		if (StringUtils.isEmpty(vo.getTitle())) {
			vo.setTitle(vo.getCategoryName());
		}
		
		if (mainDao.findRecordInProgressByPk(vo) != null) {
			mainDao.updateRecordInProgress(vo);
		}
		
		return "";
	}
	
	public String finish(RecordInProgressVo vo) throws Exception {
		
		DaumAPIVo daumProfileApiVo = (DaumAPIVo) ThreadLocalUtil.get("daumProfileApiVo");
		vo.setUserId(daumProfileApiVo.getUserId());
		
		if (StringUtils.isEmpty(vo.getTitle())) {
			vo.setTitle(vo.getCategoryName());
		}
		
		RecordInProgressVo savedRecordInProgressVo = mainDao.findRecordInProgressByPk(vo);
		if (savedRecordInProgressVo != null) {
			DaumCalendarEventVo eventVo = new DaumCalendarEventVo();
			eventVo.setTitle(vo.getTitle());
			eventVo.setDescription(vo.getDescription());
			eventVo.setLocation(vo.getLocation());
			eventVo.setCategoryId(savedRecordInProgressVo.getCategoryId());
			eventVo.setStartAt(savedRecordInProgressVo.getStartTime());
			eventVo.setEndAt(savedRecordInProgressVo.getEndTime());
			daumCalendarService.createEvent(eventVo);
			
			mainDao.removeRecordInProgress(vo);
		}
		
		return "";
	}

}
