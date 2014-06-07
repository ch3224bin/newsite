package com.john_na.statistics.web;

import java.util.Collection;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.john_na.category.service.ICategoryService;
import com.john_na.common.entity.DailyStatisticsVo;
import com.john_na.common.entity.DaumCalendarEventVo;
import com.john_na.statistics.service.IStatisticsService;
import com.john_na.statistics.service.impl.DailySearchConditionValidator;

@Controller
public class StatisticsController {
	
	@Inject
	private DailySearchConditionValidator dailySearchConditionValidator;
	
	@Inject
	private IStatisticsService StatisticsService;
	
	@Inject
	private ICategoryService categoryService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(dailySearchConditionValidator);
	}
	
	@RequestMapping("/daily.do")
	public String daily(@Valid DaumCalendarEventVo eventVo, Model model) throws Exception {
		
		Collection<DailyStatisticsVo> eventList = StatisticsService.getEventList(eventVo);
		
		model.addAttribute("eventList", eventList);
		model.addAttribute("enabledCategoryList", categoryService.getEnabledCategoryList());
		return "statistics/daily";
	}
	
	@RequestMapping("/dailyPrev.do")
	public @ResponseBody Collection<DailyStatisticsVo> dailyPrev(@Valid DaumCalendarEventVo eventVo, Model model) throws Exception {
		
		Collection<DailyStatisticsVo> eventList = StatisticsService.getEventList(eventVo);
		
		return eventList;
	}
	
	@RequestMapping("/dailyNext.do")
	public @ResponseBody Collection<DailyStatisticsVo> dailyNext(@Valid DaumCalendarEventVo eventVo, Model model) throws Exception {
		
		Collection<DailyStatisticsVo> eventList = StatisticsService.getEventList(eventVo);
		
		return eventList;
	}
}
