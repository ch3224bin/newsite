package com.john_na.statistics.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NavigableSet;
import java.util.TreeSet;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.john_na.category.service.ICategoryService;
import com.john_na.common.api.DaumCalendarService;
import com.john_na.common.entity.DailyStatisticsVo;
import com.john_na.common.entity.DaumCalendarCategoryVo;
import com.john_na.common.entity.DaumCalendarEventVo;
import com.john_na.common.util.DateUtil;
import com.john_na.statistics.service.IStatisticsService;

@Service
public class StatisticsService implements IStatisticsService {

	@Inject private ICategoryService categoryService;
	@Inject private DaumCalendarService daumCalendarService;
	
	public Collection<DailyStatisticsVo> getEventList(
			DaumCalendarEventVo eventVo) throws Exception {
		
		
		Collection<DailyStatisticsVo> resultList = new ArrayList<DailyStatisticsVo>();

		// 0. 이벤트 검색조건 설정, 이벤트 가져오기
		setSearchConditon(eventVo);
		Collection<DaumCalendarEventVo> eventList = daumCalendarService.getEventList(eventVo);
		
		// 1. 그룹핑 하기
		Collection<DaumCalendarCategoryVo> enabledCategoryList = categoryService.getEnabledCategoryList();
		
		TreeSet<String> daySet = new TreeSet<String>();
		for (DaumCalendarEventVo vo : eventList) {
			if (isManagedCategory(enabledCategoryList, vo.getCategoryId())) {
				long ms = Long.valueOf(vo.getStartAt());
				daySet.add(DateUtil.date2String(ms, "yyyy.MM.dd"));
			}
		}
		
		NavigableSet<String> descendingSet = daySet.descendingSet();
		for (String date : descendingSet) {
			DailyStatisticsVo e = new DailyStatisticsVo();
			e.setStartAt(date);
			e.setMin(0);
			e.setCount(0);
			e.setEvents(new ArrayList<DailyStatisticsVo>());
			resultList.add(e);
		}
		
		// 2. 그룹에 이벤트 넣기, 분 계산
		for (DaumCalendarEventVo event : eventList) {
			for (DailyStatisticsVo day : resultList) {
				long startMs = Long.valueOf(event.getStartAt());
				long endMs = Long.valueOf(event.getEndAt());
				String startDay = DateUtil.date2String(startMs, "yyyy.MM.dd");
				if (day.getStartAt().equals(startDay)) {
					DailyStatisticsVo e = new DailyStatisticsVo(event);
					day.getEvents().add(e);
					int min = (int) ((endMs - startMs) / 1000 / 60);
					e.setMin(min);
					e.setStartTime(DateUtil.date2String(startMs, "yyyy.MM.dd HH:mm"));
					e.setEndTime(DateUtil.date2String(endMs, "yyyy.MM.dd HH:mm"));
					e.setCategoryName(getCategoryName(enabledCategoryList, e.getCategoryId()));
					day.setMin(day.getMin() + min);
					day.setCount(day.getCount() + 1);
				}
			}
		}
		
		return resultList;
	}

	private void setSearchConditon(DaumCalendarEventVo eventVo) {
		DateTime today = new DateTime();
		eventVo.setStartAt(DateUtil.date2String(today.plusDays(-30), "yyyy-MM-dd"));
		eventVo.setEndAt(DateUtil.date2String(today.plusDays(0), "yyyy-MM-dd"));
	}
	
	private String getCategoryName(Collection<DaumCalendarCategoryVo> enabledCategoryList, String categoryId) {
		String result = "";
		
		for (DaumCalendarCategoryVo category : enabledCategoryList) {
			if (categoryId.equals(category.getId())) {
				result = category.getName();
				break;
			}
		}
		
		return result;
	}

	private boolean isManagedCategory(Collection<DaumCalendarCategoryVo> enabledCategoryList, String categoryId) {
		boolean result = false;
		
		for (DaumCalendarCategoryVo category : enabledCategoryList) {
			if (categoryId.equals(category.getId())) {
				result = true;
				break;
			}
		}
		
		return result;
	}

}
