package com.wub.handlers;

import java.util.List;

import com.wub.db.VoteStyles;
import com.wub.db.VoteStylesDAO;

public class VoteStyleHandler {

	
	private static VoteStyleHandler instance = null;
	
	
	/**
	 * Gibt alle Vote-Styles als Liste zur�ck, welche vorhanden sind. 
	 * @return Liste mit vote styles
	 */
	public List getAllVoteStyles() {
		VoteStylesDAO voteStylesDAO = new VoteStylesDAO();
		return voteStylesDAO.findAll();
	}
	
	
	
	/**
	 * Liefert einen VoteStyle zur�ck anhand der Vote-Style ID
	 * @param voteStyleId
	 * @return VoteStyles Objekt zur ID
	 */
	public VoteStyles getVoteStyleById(int voteStyleId) {
		VoteStylesDAO voteStylesDAO = new VoteStylesDAO();
		return voteStylesDAO.findById(voteStyleId);
	}
	
	
	/**
	 * Singleton Pattern
	 * @return
	 */
	public static VoteStyleHandler getInstance() {
		if (instance == null) {
			instance = new VoteStyleHandler();
		}
		return instance;
	}
	
	
}
