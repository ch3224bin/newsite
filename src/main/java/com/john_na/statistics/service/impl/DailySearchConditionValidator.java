package com.john_na.statistics.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.john_na.common.entity.DaumCalendarEventVo;

@Service
public class DailySearchConditionValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return DaumCalendarEventVo.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		DaumCalendarEventVo eventVo = (DaumCalendarEventVo) target;
	}

}
