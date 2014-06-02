package com.john_na.statistics.service;

import java.util.Collection;

import com.john_na.common.entity.DailyStatisticsVo;
import com.john_na.common.entity.DaumCalendarEventVo;

public interface IStatisticsService {
	public Collection<DailyStatisticsVo> getEventList(DaumCalendarEventVo eventVo) throws Exception;
}
