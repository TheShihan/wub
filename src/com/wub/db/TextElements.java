package com.wub.db;

/**
 * TextElements entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TextElements implements java.io.Serializable {

	// Fields

	private Integer textId;
	private String title;
	private String text;

	// Constructors

	/** default constructor */
	public TextElements() {
	}

	/** full constructor */
	public TextElements(String title, String text) {
		this.title = title;
		this.text = text;
	}

	// Property accessors

	public Integer getTextId() {
		return this.textId;
	}

	public void setTextId(Integer textId) {
		this.textId = textId;
	}
	
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

}