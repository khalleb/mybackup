package br.com.mybackup.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

//MESSAGE THAT WILL BE PRESENTED ON THE TOP SCREEN
public class Msg {
	public static void info(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
	}
	
	public static void warn(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null));
	}
	
	public static void error(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
	}
	
	public static void fatal(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, null));
	}
	
	// STICKS THE MESSAGE FROM THE FILE PROPERTIES FOR PRINTING.
	public static void msgGetProperties(String key_properties) {
		//tes
		
	}
	
}
