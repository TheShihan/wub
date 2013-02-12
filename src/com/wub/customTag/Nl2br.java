package com.wub.customTag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

/**
 * Klasse um native Zeilenumbrüche in HTML-Zeilenumbrüche umzuwandeln (XHMTL)
 * @author patric
 *
 */
public class Nl2br implements BodyTag {
	
	private BodyContent bodyContent;

	public void setPageContext(PageContext pageContext) {
	}

	public void setParent(Tag arg0) {
	}

	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	public void setBodyContent(BodyContent bodyContent) {
		this.bodyContent = bodyContent;
	}

	public void doInitBody() throws JspException {
	}

	public int doAfterBody() throws JspException {
		try {
			JspWriter jspWrite = bodyContent.getEnclosingWriter();
			String convertString = bodyContent.getString();
			jspWrite.print(convertString.replaceAll("\r\n|\n", "<br />\n"));
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public void release() {
	}

	public Tag getParent() {
		return null;
	}
}
