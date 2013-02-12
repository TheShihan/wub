package com.wub.misc;

import java.io.Serializable;

public class CommentBean implements Serializable {
	
	
	private static final long serialVersionUID = -3208925215120870564L;
	private String commentText;
	
	
	public CommentBean() {}
	

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	

}
