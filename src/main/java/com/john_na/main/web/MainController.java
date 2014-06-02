package com.john_na.main.web;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.john_na.category.service.ICategoryService;
import com.john_na.common.entity.DaumCalendarCategoryVo;
import com.john_na.common.entity.RecordInProgressVo;
import com.john_na.main.service.IMainService;

@Controller
public class MainController {
	
	@Inject private IMainService mainService;
	@Inject private ICategoryService categoryService;
	
	@RequestMapping("/main.do")
	public String main(Model model) throws Exception {
		Collection<DaumCalendarCategoryVo> categoryList = categoryService.getEnabledCategoryList();
		RecordInProgressVo recordInProgress = mainService.getRecordInProgress();
		
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("recordInProgress", recordInProgress);
		return "main/main";
	}
	
	@RequestMapping("/start.do")
	public String start(RecordInProgressVo recordInProgressVo) throws Exception {
		mainService.start(recordInProgressVo);
		return "redirect:main.do";
	}
	
	@RequestMapping("/update.do")
	public String update(RecordInProgressVo recordInProgressVo) throws Exception {
		mainService.update(recordInProgressVo);
		return "redirect:main.do";
	}
	
	@RequestMapping("/finish.do")
	public String finish(RecordInProgressVo recordInProgressVo) throws Exception {
		mainService.finish(recordInProgressVo);
		return "redirect:main.do";
	}
}
