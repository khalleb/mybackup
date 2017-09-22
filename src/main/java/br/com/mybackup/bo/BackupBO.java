package br.com.mybackup.bo;

import br.com.mybackup.dao.BackupDAO;
import br.com.mybackup.to.DataBackupTO;

public class BackupBO {
	BackupDAO	backupDAO;
	
	public BackupBO() {
		try {
			backupDAO = new BackupDAO();
		}
		catch (Exception erro) {
			System.out.println("Error construtor BackupBO " + erro.getMessage());
		}
	}
	
	public String consistsDataBackup(DataBackupTO dataBackupTO) {
		if (dataBackupTO.getPgDump().equals("") || dataBackupTO.getPgDump() == null) {
			return "Input pgDump path is empty!";
		}
		if (dataBackupTO.getHostName().equals("") || dataBackupTO.getHostName() == null) {
			return "Input HostName empty!";
		}
		if (dataBackupTO.getPortComunication().equals("") || dataBackupTO.getPortComunication() == null) {
			return "Input Porta Comunication empty!";
		}
		if (dataBackupTO.getDatabase().equals("") || dataBackupTO.getDatabase() == null) {
			return "Input Database empty!";
		}
		if (dataBackupTO.getUser().equals("") || dataBackupTO.getUser() == null) {
			return "Input User empty!";
		}
		if (dataBackupTO.getPassword().equals("") || dataBackupTO.getPassword() == null) {
			return "Input Password empty!";
		}
		if (dataBackupTO.getLocationBackup().equals("") || dataBackupTO.getLocationBackup() == null) {
			return "Input Location Backup empty!";
		}
		if (dataBackupTO.getTimeBackup().equals("") || dataBackupTO.getTimeBackup() == null) {
			return "Input Time Backup empty!";
		}
		return "ok";
	}
	
	public String consistsCreateProperties(DataBackupTO dataBackupTO) {
		String returnConsistsDataBackup = consistsDataBackup(dataBackupTO);
		if (returnConsistsDataBackup.equalsIgnoreCase("ok")) {
			backupDAO.createProperties(dataBackupTO);
			return returnConsistsDataBackup;
		}
		return returnConsistsDataBackup;
	}
}