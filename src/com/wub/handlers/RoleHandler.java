package com.wub.handlers;

import java.util.List;

import com.wub.db.Administrators;
import com.wub.db.Roles;
import com.wub.db.RolesDAO;

public class RoleHandler {

	public enum AdminRole { USERMANAGER, REPORTMANAGER, APPRAISALMANAGER };
	
	private static RoleHandler instance = null;
	
	
	/**
	 * Gibt alle (Berechtigungs-)Rollen als Liste zurück, welche vorhanden sind. 
	 * @return Liste mit Administrator-Rollen
	 */
	public List getAllRoles() {
		RolesDAO rolesDao = new RolesDAO();
		return rolesDao.findAll();
	}
	
	
	/**
	 * Findet die Rolle anhand der Rollen-ID
	 * @param roleId Die ID der Rolle
	 * @return Instanz der Rolle
	 */
	public Roles getRoleById(int roleId) {
		RolesDAO rolesDao = new RolesDAO();
		return rolesDao.findById(roleId);
	}
	
	
	/**
	 * Prüft ob ein Administrator Berechtigung für einen bestimmten Bereich hat.
	 * @param role Der Rollentyp der geprüft wird
	 * @param adminId die ID des Administrators
	 * @return true = hat Berechtigung, false = keine Berechtigung
	 */
	public boolean checkAccess(int adminId, AdminRole roleType) {
		boolean grantAccess = false;
		
		Administrators admin =
			AdminUserHandler.getInstance().getAdminById(adminId);
		int roleId = admin.getRoleId();
		Roles role = getRoleById(roleId);
		
		switch (roleType) {
			case APPRAISALMANAGER:
				if (role.getAppraisalmanager() != null &&
					role.getAppraisalmanager() == 1) {
					grantAccess = true;
				}
				break;
			case REPORTMANAGER:
				if (role.getReportmanager() != null &&
						role.getReportmanager() == 1) {
						grantAccess = true;
					}
				break;
			case USERMANAGER:
				if (role.getUsermanager() != null &&
						role.getUsermanager() == 1) {
						grantAccess = true;
					}
				break;
		}
		
		return grantAccess;
	}
	
	
	/**
	 * Singleton Pattern
	 * @return
	 */
	public static RoleHandler getInstance() {
		if (instance == null) {
			instance = new RoleHandler();
		}
		return instance;
	}
	
	
}
