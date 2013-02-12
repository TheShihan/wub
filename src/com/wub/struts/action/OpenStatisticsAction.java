/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.wub.struts.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wub.db.Administrators;
import com.wub.handlers.AdminUserHandler;
import com.wub.handlers.AppraisalHandler;
import com.wub.handlers.RoleHandler;
import com.wub.handlers.RoleHandler.AdminRole;

/** 
 * MyEclipse Struts
 * Creation date: 12-09-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class OpenStatisticsAction extends Action {
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
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		
		HttpSession session = request.getSession();
		
		if(session != null && session.getAttribute(AdminUserHandler.ADMINDATA) != null) {
			// Berechtigung pr�fen
			RoleHandler roleHandler = RoleHandler.getInstance();
			Administrators admin = (Administrators)request.getSession().getAttribute(AdminUserHandler.ADMINDATA);
			
			if (roleHandler.checkAccess(admin.getAdminId(), AdminRole.REPORTMANAGER)) {
				// user ist eingeloggt und berechtigt, Listen mit Statistiken vorbereiten
				
				/* Folgende Statisiken machen prim�r Sinn (man k�nnte aber auch
				 * noch Weitere machen):
				 * 
				 * - nach Lehrer
				 * - Nach Unterrichts-Fach
				 * - Nach Beurteilung
				 */
				
				AppraisalHandler appHandler = AppraisalHandler.getInstance();
				
				List usedSubjectsList = appHandler.getUsedSubjects();
				List usedAdminsList = AdminUserHandler.getInstance().getUsedAdmins();
				List sentClassList = appHandler.getClassListSent();
				
				// Listen sortieren
				Collections.sort(usedSubjectsList);
				Collections.sort(usedAdminsList);
				Collections.sort(sentClassList);
				
				
				// Listen f�r sp�tere Verwendung im request speichern.
				request.setAttribute("subjectList", usedSubjectsList);
				request.setAttribute("teacherList", usedAdminsList);
				request.setAttribute("classList", sentClassList);
				
				// Auswahlseite aufrufen
				return mapping.findForward("showStatistics");
			} else {
				// Kein Zutritt
				return mapping.findForward("adminPermissionMissing");
			}
		}
		
		
		// nicht eingeloggt -> zur Startseite leiten
		return mapping.findForward("start");
		
	}
}