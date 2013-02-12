/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.wub.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wub.handlers.AdminUserHandler;
import com.wub.handlers.AppraisalHandler;
import com.wub.handlers.SettingsHandler;
import com.wub.struts.form.SettingListForm;
import com.wub.struts.form.TextElementListForm;

/** 
 * MyEclipse Struts
 * Creation date: 02-13-2008
 * 
 * XDoclet definition:
 * @struts.action scope="request" validate="true"
 */
public class OpenSettingsAction extends Action {
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

		SettingListForm settingListForm = (SettingListForm)form;
		
		if(request.getSession().getAttribute(AdminUserHandler.ADMINDATA) != null) {
			
			// Liste der Settings vorbereiten
			if (settingListForm == null) {
				settingListForm = new SettingListForm();
			}
			
			// abf�llen der Setting-Elemente
			SettingsHandler settingsHandler = SettingsHandler.getInstance();
			List settingsList = settingsHandler.getAllSettings();
			
		
			settingListForm.setSettingList(settingsList);
			
			// wir m�ssen die Liste im Scope speichern, damit sie auf der Seite verf�gbar ist
			request.setAttribute(SettingsHandler.SETTINGSLISTFORM, settingListForm);
			
			return mapping.findForward("toSettings");
		}
		
		// nicht eingeloggt -> zu startseite leiten
		return mapping.findForward("start");
		
		
	}
}