package com.john_na.common.util;

import org.eclipse.mylyn.wikitext.confluence.core.ConfluenceLanguage;
import org.eclipse.mylyn.wikitext.core.parser.MarkupParser;

public class WikiMarkupUtil {
	
	private static final MarkupParser markupParser = new MarkupParser(new ConfluenceLanguage());
	
	public static String wikiMarkup2Html(String wiki) {
		String html = markupParser.parseToHtml(wiki);
		html = html.substring(html.indexOf("<body>") + "<body>".length() , html.indexOf("</body>"));
		return html;
	}
}
