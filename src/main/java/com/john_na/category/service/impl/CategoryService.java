package com.john_na.category.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import org.anyframe.util.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import com.john_na.category.service.ICategoryService;
import com.john_na.common.api.DaumCalendarService;
import com.john_na.common.entity.DaumAPIVo;
import com.john_na.common.entity.DaumCalendarCategoryVo;
import com.john_na.common.entity.DaumCalendarManageVo;

@Service
public class CategoryService implements ICategoryService {
	
	@Inject private CategoryDao categoryDao;
	@Inject private DaumCalendarService daumCalendarService;
	
	public Collection<DaumCalendarCategoryVo> getEnabledCategoryList() throws Exception {
		Collection<DaumCalendarCategoryVo> resutList = new ArrayList<DaumCalendarCategoryVo>();
		
		DaumAPIVo daumProfileApiVo = (DaumAPIVo) ThreadLocalUtil.get("daumProfileApiVo");
		Collection<DaumCalendarManageVo> managedList = categoryDao.findDaumCalendarManageList(daumProfileApiVo.getUserId());
		
		Collection<DaumCalendarCategoryVo> categoryList = daumCalendarService.getCategoryList();
		for (DaumCalendarCategoryVo category : categoryList) {
			if (containsManageList(managedList, category)) {
				resutList.add(category);
			}
		}
		
		return resutList;
	}

	public Collection<DaumCalendarCategoryVo> getCategoryList()
			throws Exception {
		
		DaumAPIVo daumProfileApiVo = (DaumAPIVo) ThreadLocalUtil.get("daumProfileApiVo");
		Collection<DaumCalendarManageVo> managedList = categoryDao.findDaumCalendarManageList(daumProfileApiVo.getUserId());
		
		Collection<DaumCalendarCategoryVo> categoryList = daumCalendarService.getCategoryList();
		for (DaumCalendarCategoryVo category : categoryList) {
			category.setUseYn(containsManageList(managedList, category) ? "Y" : "N");
		}
		
		return categoryList;
	}
	
	private boolean containsManageList(Collection<DaumCalendarManageVo> managedList, DaumCalendarCategoryVo category) {
		if (managedList == null) return false;
		boolean result = false;
		for (DaumCalendarManageVo managedCategory : managedList) {
			if (category.getId().equals(managedCategory.getCategoryId())) {
				result = true;
				break;
			}
		}
		
		return result;
	}

	public String changeCategoryUseyn(String id, String useYn) throws Exception {
		DaumAPIVo daumProfileApiVo = (DaumAPIVo) ThreadLocalUtil.get("daumProfileApiVo");
		
		DaumCalendarManageVo vo = new DaumCalendarManageVo();
		vo.setUserId(daumProfileApiVo.getUserId());
		vo.setCategoryId(id);
		
		if ("Y".equals(useYn)) {
			categoryDao.updateDaumCalendarManage(vo);
		} else {
			categoryDao.removeDaumCalendarManage(vo);
		}
		return "";
	}
}
