package br.com.mybackup.util;

import java.util.ResourceBundle;

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
	public static String msgGetProperties(String key_properties) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("messages_pt_BR");
		resourceBundle = ResourceBundle.getBundle("messages_pt_BR");
		System.out.println(resourceBundle.getString(key_properties));
		return resourceBundle.getString(key_properties);
	}
	
	public static void versionSystem() {
		System.out.println("  __  __       ____             _              __   __");
		System.out.println(" |  \\/  |     |  _ \\           | |             \\ \\ / /");
		System.out.println(" | \\  / |_   _| |_) | __ _  ___| | ___   _ _ __ \\ V / ");
		System.out.println(" | |\\/| | | | |  _ < / _` |/ __| |/ / | | | '_ \\ > <  ");
		System.out.println(" | |  | | |_| | |_) | (_| | (__|   <| |_| | |_) / . \\ ");
		System.out.println(" |_|  |_|\\__, |____/ \\__,_|\\___|_|\\_\\\\__,_| .__/_/ \\_\\");
		System.out.println("          __/ |                           | |");
		System.out.println("         |___/                            |_|");
		
		msgGetProperties("sistema.nome");
		msgGetProperties("sistema.versao");
		System.out.println("\n");
		// LINK PARA CRIAR ARTE
		// http://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20
		// FONT: Big
		//Character Width: Default
		//Character Height: Default
		
	}
	
}
