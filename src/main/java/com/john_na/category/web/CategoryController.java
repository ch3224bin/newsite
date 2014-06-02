package com.john_na.category.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import com.john_na.category.service.ICategoryService;
import com.john_na.common.view.JsonView;

@Controller
public class CategoryController {
	
	@Inject
	private ICategoryService categoryService;
	
	@RequestMapping("/categoryManage.do")
	public String categoryManage(Model model) throws Exception {
		model.addAttribute("categoryList", categoryService.getCategoryList());
		return "category/category_manage";
	}
	
	@RequestMapping("/changeCategoryUseyn.do")
	public View changeCategoryUseyn(@RequestParam(value="id")String id, @RequestParam(value="useYn")String useYn) throws Exception {
		categoryService.changeCategoryUseyn(id, useYn);
		return new JsonView();
	}
}
