/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.wub.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wub.db.Users;
import com.wub.handlers.UserHandler;

/** 
 * MyEclipse Struts
 * Creation date: 02-23-2008
 * 
 * XDoclet definition:
 * @struts.action
 */
public class PrepareAppraisalListAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		if (session != null) {
			Users userData = (Users)session.getAttribute(UserHandler.USERDATA);
			if (userData != null) {
				/* ein g�ltiges User Objekt wurde gefunden, Login ist OK
				 * Liste mit g�ltigen Beurteilungen vorbereiten und im request ablegen.
				 * Danach auf eine Auswahlseite weiterleiten.
				 */
				List appraisalList = UserHandler.getInstance().getValidAppraisals(userData.getUserId());
				
				// wenn Liste leer ist, wird der User entsprechend informiert, sonst weitergeleitet
				if (appraisalList == null || appraisalList.size() == 0) {
					// informieren (Session invalidieren, da der User diese nicht mehr braucht)
					session.invalidate();
					return mapping.findForward("noAppraisals");
				} else {
					// Liste speichern und weiterleiten
					request.setAttribute("appraisalList", appraisalList);
					
					// User weiterleiten auf n�chste Seite
					return mapping.findForward("showList");
				}
			}
		}
		
		// Fallback
		return mapping.findForward("startUser");
		
	}
}