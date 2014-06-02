package com.john_na.statistics.web;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.john_na.category.service.ICategoryService;
import com.john_na.common.entity.DailyStatisticsVo;
import com.john_na.common.entity.DaumCalendarEventVo;
import com.john_na.statistics.service.IStatisticsService;

@Controller
public class StatisticsController {
	
	@Inject
	private IStatisticsService StatisticsService;
	
	@Inject
	private ICategoryService categoryService;
	
	@RequestMapping("/daily.do")
	public String getEventList(DaumCalendarEventVo eventVo, Model model) throws Exception {
		
		Collection<DailyStatisticsVo> eventList = StatisticsService.getEventList(eventVo);
		
		model.addAttribute("eventList", eventList);
		model.addAttribute("enabledCategoryList", categoryService.getEnabledCategoryList());
		return "statistics/daily";
	}
}
