package com.wub.handlers;

import java.util.ArrayList;
import java.util.List;

import com.wub.db.Administrators;
import com.wub.db.AdministratorsDAO;



/**
 * Class um Administrations-Benützer zu verwalten
 * @author Patric Schielke
 *
 */
public class AdminUserHandler {
	
	public static final String ADMINDATA = "adminData";
	public static final String ADMINISTRATOREDITFORM = "administratorEditForm";
	public static final String ADMINLIST = "adminListForm";
	private static AdminUserHandler instance = null;
	
	
	/**
	 * Constructor
	 */
	private AdminUserHandler() {}
	
	
	
	/**
	 * Überprüft ob ein Benutzer mit dem angegebenen Kennwort in der DB vorhanden ist.
	 * @param username Benutzername der überprüft wird.
	 * @param password Kennwort das zum Benutzer gehört
	 * @return Instaz von Administrators wenn Login erfolgreich 
	 */
	public Administrators checkLogin(String username, String password) {

		//AdminUserBo adminUser = new AdminUserBo();
		Administrators adminEntry;

		adminEntry = getAdminRow(username, password);
		if (adminEntry == null) {
			return null;
		}
		
		return adminEntry;
	}
	
	
	/**
	 * Ändert einen bisherigen Administrators Datensatz
	 * @param oldAdmin
	 */
	public void editAdministrator(Administrators oldAdmin) {
		AdministratorsDAO adminDAO = new AdministratorsDAO();
		adminDAO.attachClean(oldAdmin);
	}
	
	/**
	 * Speichert einen neuen Administratoren Datensatz in der Datenbank
	 * @param newAdmin
	 */
	public void createAdministrator(Administrators newAdmin) {
		AdministratorsDAO adminDAO = new AdministratorsDAO();
		adminDAO.save(newAdmin);
	}
	
	
	/**
	 * Gibt eine eine Zeile mit Admin-Informationen zurück.
	 * @param username
	 * @param password
	 * @return
	 */
	private Administrators getAdminRow(String username, String password) {

		AdministratorsDAO administratorsDAO = new AdministratorsDAO();

		List<Administrators> adminList = administratorsDAO.checkLogin(username, password);

		if (adminList == null || adminList.isEmpty()) {
			return null;
		} else {
			return adminList.get(0);
		}
	}
	
	
	/**
	 * Gibt eine Liste mit allen Administatoren zurück
	 * @return
	 */
	public List getAdministrators() {
		AdministratorsDAO administratorsDAO = new AdministratorsDAO();
		return administratorsDAO.findAll();
	}
	
	
	
	/**
	 * Holt eine Liste, mit allen Administratoren/Lehrern,
	 * welche in einer versendeten Beurteilung eingetragen sind.
	 * @return Liste mit verwendeten Lehrern
	 */
	public List getUsedAdmins() {
		List<Administrators> allAdmins = getAdministrators();
		List<Administrators> usedAdmins = new ArrayList<Administrators>();
		for (Administrators admin : allAdmins) {
			if (AppraisalHandler.getInstance().checkAdminInUse(admin.getAdminId())) {
				// zur Liste hinzufügen
				usedAdmins.add(admin);
			}
		}
		return usedAdmins;
	}
	
	
	/**
	 * Kennwort für Administrator ändern
	 * @param username Benutzername für den das Kennwort geändert wird
	 * @param password Passwort für den Benutzer
	 * @param newPassword Das neue Kennwort für den Benutzer
	 * @return true, wenn erfolgreich, ansonsten false
	 */
	public boolean changePassword(String username, String password,	String newPassword) {
		
		AdministratorsDAO adminDAO = new AdministratorsDAO();
		adminDAO.getSession().beginTransaction();
		
		Administrators adminEntry;
		adminEntry = getAdminRow(username, password);
		if (adminEntry == null) {
			return false;
		}
		// neues Kennwort festlegen
		adminEntry.setPassword(newPassword);
		
		// speichern in DB
		adminDAO.save(adminEntry);
		adminDAO.getSession().getTransaction().commit();
		
		return true;
	}
	
	
	
	/**
	 * Administrator Daten anhand der ID auslesen
	 * @param id die Admin id
	 * @return Administrators Objekt zur jeweiligen ID
	 */
	public Administrators getAdminById(int id) {
		AdministratorsDAO adminDAO = new AdministratorsDAO();
		return adminDAO.findById(id);
	}
	
	
	/**
	 * Löscht einen Administrator anhand seiner admin Id. Löschen nur möglich, wenn keine aktiven
	 * Beurteilungen zugewiesen.
	 * @param id die ID des Admins
	 * @return true wenn gelöscht, ansonsten false
	 */
	public boolean deleteAdminById(int id) {
		List classList =
			AppraisalHandler.getInstance().getClassListByAdminId(id);
		if (classList == null || classList.size() == 0)
		{
			AdministratorsDAO adminDAO = new AdministratorsDAO();
			adminDAO.delete(getAdminById(id));
			return true;
		}
		
		return false;
	}
	
		
	
	/**
	 * Singleton Pattern
	 * @return
	 */
	public static AdminUserHandler getInstance() {
		if (instance == null) {
			instance = new AdminUserHandler();
		}
		return instance;
	}

}
