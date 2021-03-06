/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.wub.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import com.wub.db.Administrators;
import com.wub.handlers.AdminUserHandler;
import com.wub.misc.Helper;
import com.wub.struts.form.AdministratorEditForm;

/** 
 * MyEclipse Struts
 * Creation date: 12-10-2007
 * 
 * XDoclet definition:
 * @struts.action path="/administratorEdit" name="administratorEditForm" input="/administratorEdit.jsp" parameter="do" scope="request" validate="true"
 */
public class AdministratorEditAction extends DispatchAction {

	
	/**
	 * Bereitet das editieren eines Administrators vor
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editAdmin(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		
		AdministratorEditForm administratorEditForm = (AdministratorEditForm) form;
		if (administratorEditForm == null) {
			administratorEditForm = new AdministratorEditForm();
		}
		
		// id aus request lesen
		int id = Integer.parseInt(request.getParameter("adminId"));
		
		// admin info auslesen und in session speichern
		Administrators adminDataRow = AdminUserHandler.getInstance().getAdminById(id);
		
		// die daten werden in einer form gespeichert, welche in der session abgespeichert wird
		administratorEditForm.setId(adminDataRow.getAdminId());
		administratorEditForm.setName(adminDataRow.getName());
		administratorEditForm.setEmail(adminDataRow.getEmail());
		administratorEditForm.setPassword(adminDataRow.getPassword());
		administratorEditForm.setRole(adminDataRow.getRoleId());
		
		request.getSession().setAttribute(AdminUserHandler.ADMINISTRATOREDITFORM, administratorEditForm);
		
		return mapping.findForward("showEdit");
		
	}
	
	/** 
	 * Method deleteAdmin
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward deleteAdmin(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		
		// id aus request lesen
		int id = Integer.parseInt(request.getParameter("adminId"));
		
		// admin anhand seiner ID finden und l�schen
		AdminUserHandler.getInstance().deleteAdminById(id);
		
		return mapping.findForward("showList");
	}
	
	/** 
	 * Methode addAdmin leitet weiter auf die Seite zum hinzuf�gen eines 
	 * neuen Administrators.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addAdmin(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		
		//AdministratorEditForm administratorEditForm = (AdministratorEditForm) form;
		
		return mapping.findForward("showAdd");
		
	}
	
	/** 
	 * Methode saveAdmin speichert einen neuen Administrator.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward saveAdmin(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		
		AdministratorEditForm administratorEditForm = (AdministratorEditForm) form;
		
		// wir machen die form Validierung hier, da es mit einer Dispatch Action sonst
		// nicht ganz so trivial ist
		
		if (administratorEditForm.getName() != null &&
				administratorEditForm.getName().length() > 0 &&
				administratorEditForm.getPassword() != null &&
				administratorEditForm.getPassword().length() > 0 &&
				administratorEditForm.getEmail() != null &&
				administratorEditForm.getEmail().trim().length() > 0 &&
				Helper.getInstance().isValidEmailAddress(administratorEditForm.getEmail().trim()))
		{
			// Daten ok
			
			// wenn eine ID vorhanden ist, wird diese wiederverwendet, ansonsten wird
			// ein neuer Datensatz angelegt werden
			Integer adminId = administratorEditForm.getId();
			if (adminId != null && adminId > 0) {
				// bestehender Eintrag
				Administrators oldAdmin =AdminUserHandler.getInstance().getAdminById(adminId);
				oldAdmin.setName(administratorEditForm.getName());
				oldAdmin.setEmail(administratorEditForm.getEmail());
				oldAdmin.setPassword(administratorEditForm.getPassword());
				oldAdmin.setRoleId(administratorEditForm.getRole());
				
				AdminUserHandler.getInstance().editAdministrator(oldAdmin);
			}
			else 
			{
				// neuer Datensatz
				Administrators newAdminRow = new Administrators();
				newAdminRow.setName(administratorEditForm.getName());
				newAdminRow.setEmail(administratorEditForm.getEmail().trim());
				newAdminRow.setPassword(administratorEditForm.getPassword());
				newAdminRow.setRoleId(administratorEditForm.getRole());
				
				AdminUserHandler.getInstance().createAdministrator(newAdminRow);				
			}
		}
		else {
			// Daten fehlerhaft -> nochmals anzeigen
			MessageResources msg = MessageResources.getMessageResources("com.wub.struts.ApplicationResources");
			
			if (administratorEditForm.getEmail() != null &&
				!Helper.getInstance().isValidEmailAddress(administratorEditForm.getEmail())) {
				administratorEditForm.setInfoMessage(msg.getMessage("error.admin.administratoredit.emailformat"));
				administratorEditForm.setEmail("");
			} else {
				// Felder nicht ausgef�llt
				administratorEditForm.setInfoMessage(msg.getMessage("error.admin.administratoredit.general"));
			}
			
			// Form wieder in request schreiben	
			request.getSession().setAttribute(AdminUserHandler.ADMINISTRATOREDITFORM, administratorEditForm);
			if (administratorEditForm.getId() != null) {
				// alter Eintrag
				return mapping.findForward("showEdit");
				
			} else {
				// neuer Eintrag
				return mapping.findForward("showAdd");
			}
		}
		
		// Liste anzeigen
		return mapping.findForward("showList");
		
	}
	
	
}