package com.wub.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wub.db.Administrators;
import com.wub.handlers.AdminUserHandler;

public class InitAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	
		// prüfen ob der Benutzer sich schonmal angemeldet hatte während dieser Session
		HttpSession session = request.getSession();
		
		if (session != null) {
			Administrators adminEntry = (Administrators)session.getAttribute(AdminUserHandler.ADMINDATA);
			if (adminEntry != null)
			{
				return mapping.findForward("relogin");
			}
		}
		
		// wenn noch nicht eingeloggt: standardmässig auf die Anmelde-Seite 
		return mapping.findForward("initOk");

	}
	
}
