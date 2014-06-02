package com.john_na.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.john_na.common.util.WikiMarkupUtil;

public class WikiMarkup2Html extends TagSupport  {
	private static final long serialVersionUID = 1L;
	
	private String value;

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int doStartTag() throws JspException {
		
		
		if ( StringUtils.isNotEmpty(value) ) {
			
			try {
				String html = WikiMarkupUtil.wikiMarkup2Html(value);
				pageContext.getOut().println(html);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		value = null;
		
		return EVAL_PAGE;
	}
}
