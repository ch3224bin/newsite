package com.john_na.category.service;

import java.util.Collection;

import com.john_na.common.entity.DaumCalendarCategoryVo;

public interface ICategoryService {
	public Collection<DaumCalendarCategoryVo> getCategoryList() throws Exception;
	public Collection<DaumCalendarCategoryVo> getEnabledCategoryList() throws Exception;
	public String changeCategoryUseyn(String id, String useYn) throws Exception;
}
