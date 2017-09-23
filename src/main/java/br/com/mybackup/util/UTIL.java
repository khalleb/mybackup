package br.com.mybackup.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.mybackup.to.DataBackupTO;

public class UTIL {
	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	// PEGA NO TO A HORA E FAZ UM SUBSTRING DA HORA E RETORNA A HORA
	public String getHourView(DataBackupTO dataBackupTO) {
		String hora = dataBackupTO.getTimeBackup().substring(0, 2); // 16:55 --> 16
		return hora;
	}
	
	// PEGA NO TO A HORA E FAZ UM SUBSTRING DO MINUTO E RETORNA O MINUTOS
	public String getMinuteView(DataBackupTO dataBackupTO) {
		String minuto = dataBackupTO.getTimeBackup().substring(3, 5); // 16:55 --> 55
		return minuto;
	}
	
	public static void versionSystem() {
		System.out.println(" __  __           ____                   _                          __  _  __   ");
		System.out.println("|\\ \\/  |         |  _ \\                 | |                        / / | | \\ \\  ");
		System.out.println("| \\  / |  _   _  | |_) |   __ _    ___  | | __  _   _   _ __      | |  | |  | | ");
		System.out.println("| |\\/| | | | | | |  _ <   / _` |  / __| | |/ / | | | | | '_ \\    / /   | |   \\ \\");
		System.out.println("| |  | | | |_| | | |_) | | (_| | | (__  |   <  | |_| | | |_) |   \\ \\   |_|   / /");
		System.out.println("|_|  |_|  \\__, | |____/   \\__,_|  \\___| |_|\\_\\  \\__,_| | .__/     | |  (_)  | | ");
		System.out.println("           __/ |                                       | |         \\_\\     /_/  ");
		System.out.println("          |___/                                        |_|                      ");
		
		Msg.msgGetProperties("sistema.nome");
		Msg.msgGetProperties("sistema.versao");
		// http://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20
		// LINK PARA CRIAR ARTE
	}
}
