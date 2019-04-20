package br.com.mybackup.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		String hora = dataBackupTO.getHorarioBackup().substring(0, 2); // 16:55 --> 16
		return hora;
	}
	
	// PEGA NO TO A HORA E FAZ UM SUBSTRING DO MINUTO E RETORNA O MINUTOS
	public String getMinuteView(DataBackupTO dataBackupTO) {
		String minuto = dataBackupTO.getHorarioBackup().substring(3, 5); // 16:55 --> 55
		return minuto;
	}
	
	public static String obterHorarioAtual() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
		String dataFormatada = sdf.format(hora);
		return dataFormatada;
	}
	
}
