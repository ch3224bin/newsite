package com.john_na.common.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.google.gson.Gson;

public class JsonView extends AbstractView {
	
	private String strJson = null;
	
	public JsonView() {}
	
	public JsonView(String json) {
		this.strJson = json;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String json = "";
		if ( StringUtils.isNotEmpty(strJson) ) {
			json = strJson;
		} else {
			Gson gson = new Gson();
			json = gson.toJson(model.get("result"));
		}
		
		
		response.setContentType("application/json;charset=utf-8"); 
		response.getWriter().write(json);
	}

}
