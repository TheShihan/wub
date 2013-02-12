package com.wub.misc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Hilfsklasse für allerlei Funktionen
 * 
 * @author patric
 * 
 */
public class Helper {
	
	private static Helper instance;
	
	/**
	 * Privater Konstruktor
	 */
	private Helper() {}
	
	
	/**
	 * Für die Singleton implementierung. Gibt die Instanz dieser Klasse zurück.
	 * 
	 * @return
	 */
	public static Helper getInstance() {
		if (instance == null) {
			instance = new Helper();
		}
		return instance;
	}
	
	
	/**
	 * Gibt die Jahre des Copyrights zurück
	 * 
	 * @return
	 */
	public String getCopyright()
	{
		int copyrightStartYear = 2007;
		
		// aktuelles Jahr herausfinden
		GregorianCalendar gc = new GregorianCalendar();
		int currentYear = gc.get(GregorianCalendar.YEAR);
		
		if (currentYear == copyrightStartYear) {
			return Integer.toString(currentYear);
		}
		else {
			// Zeitspanne
			return Integer.toString(copyrightStartYear) + "-" + Integer.toString(currentYear);
		}
	}
	
	
	/**
	 * Prüft ob ein Datum gültig ist
	 * 
	 * @param inputDate
	 * @return
	 */
	public boolean ValidateDate(String inputDate) {
		Date dtTmp;
		try {
			String days = inputDate.substring(0, 2);
			String month = inputDate.substring(3, 5);
			if (Integer.parseInt(days) > 0 && Integer.parseInt(days) < 32 &&
 					Integer.parseInt(month) > 0 && Integer.parseInt(month) < 13) {
				dtTmp = new SimpleDateFormat("dd.MM.yyyy").parse(inputDate);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
			
		return false;
	}
	
	
	
	/**
	 * Konvertiert einen String in ein Datum
	 * 
	 * @param inputDate
	 * @return
	 */
	public Date ConvertDate(String inputDate) {
		Date dtTmp;
		try {
			dtTmp = new SimpleDateFormat("dd.MM.yyyy").parse(inputDate);
		} catch (ParseException e) {
			dtTmp = new Date();
			dtTmp.setTime(System.currentTimeMillis());
		}
		
		return dtTmp;
	}
	
	
	/**
	 * Validiert ein Jahres-String.
	 * 
	 * @param year
	 * @return
	 */
	public boolean ValidateYear(String year) {
		Date dtTmp;
		try {
			dtTmp = new SimpleDateFormat("yyyy").parse(year);
		} catch (ParseException e) {
			return false;
		}
		
		if (dtTmp == null) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Konvertiert ein Jahres-String in ein Datum
	 * 
	 * @param year
	 * @return
	 */
	public Date ConvertYear(String year) {
		Date dtTmp;
		try {
			dtTmp = new SimpleDateFormat("yyyy").parse(year);
		} catch (ParseException e) {
			dtTmp = new Date();
			dtTmp.setTime(System.currentTimeMillis());
		}
		return dtTmp;
	}
	
	
	/**
	 * Nimmt ein Date Objekt auf und gibt dieses als String im Format
	 * Tag.Monat.Jahr zurück.
	 * 
	 * @param inputDate
	 * @return
	 */
	public String GetStandardDate(Date inputDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(inputDate);
		String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String year = Integer.toString(cal.get(Calendar.YEAR));
		if (day.length() == 1) {
			day = "0" + day;
		}
		if (month.length() == 1) {
			month = "0" + month;
		}
		return day + "." + month + "." + year;
	}
	
	
	/**
	 * Nimmt ein Date Objekt auf und gibt dieses als String im Format "JAHR"
	 * zurück.
	 * 
	 * @param inputDate
	 * @return
	 */
	public String GetStandardDateYearOnly(Date inputDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(inputDate);
		return Integer.toString(cal.get(Calendar.YEAR));
	}
	
	
	
	/**
	 * "Kapselt" die Java Mail API. Sendet ein Email.
	 * @param smtpServer Der SMTP Server
	 * @param to Die Empfängeradresse
	 * @param from Die Absenderadresse
	 * @param subject Der Mail-Titel
	 * @param body Der Inhalt des Mails
	 * @throws Exception
	 */
	public void sendMail(String smtpServer, String to, String from, String subject, String body) throws Exception {
		sendMail(smtpServer, to, from, subject, body, null, null);
	}
	
	
	/**
	 * "Kapselt" die Java Mail API. Sendet ein Email.
	 * @param smtpServer Der SMTP Server
	 * @param to Die Empfängeradresse
	 * @param from Die Absenderadresse
	 * @param subject Der Mail-Titel
	 * @param body Der Inhalt des Mails
	 * @param smtpUser Der Benutzer für den SMTP Server
	 * @param smtpPassword Das Kennwort für den SMTP Benutzer
	 * @throws Exception 
	 */
	public void sendMail(String smtpServer, String to, String from, String subject, String body, String smtpUser, String smtpPassword) throws Exception {
		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.host", smtpServer);
			props.put("mail.smtp.starttls.enable", "true");
			Session session = Session.getDefaultInstance(props, null);
			// -- Create a new message --
			Message msg = new MimeMessage(session);
			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(to, false));
			// -- We could include CC recipients too --
			// if (cc != null)
			//msg.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc, false));
			// -- Set the subject and body text --
			msg.setSubject(subject);
			msg.setText(body);
			// -- Set some other header information --
			msg.setHeader("X-Mailer", "WubTechEmail");
			msg.setSentDate(new Date());
			// -- Send the message --
			Transport tr = session.getTransport("smtp");
			if (smtpUser != null && smtpPassword != null) {
				tr.connect(smtpServer, smtpUser, smtpPassword);
			} else {
				tr.connect();
			}
			msg.saveChanges();      // don't forget this
			tr.sendMessage(msg, msg.getAllRecipients());
			tr.close();
			System.out.println("-- Mail Message sent OK. --");
	    }
		catch (Exception ex)
		{
			System.out.println("Exception thrown: " + ex.getLocalizedMessage());
			ex.printStackTrace();
			throw ex;
		}
	}
	
	
	
	
	/**
	 * Validiert eine Email-Adresse
	 * @param email die Adresse die geprüft wird.
	 * @return true = gültig, false = ungültig
	 */
	public boolean isValidEmailAddress(String email) {
		// Patternstring
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		// String mit Pattern vergleichen
		Matcher m = p.matcher(email);
		boolean matchFound = m.matches();
		if (matchFound) {
			return true;
		} else {
			return false;
		}
	}
	
	

}
