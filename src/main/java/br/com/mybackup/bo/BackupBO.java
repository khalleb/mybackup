package br.com.mybackup.bo;

import java.io.File;

import br.com.mybackup.dao.BackupDAO;
import br.com.mybackup.dao.ConectionDAO;
import br.com.mybackup.dao.PostgresDAO;
import br.com.mybackup.to.DataBackupTO;

public class BackupBO {
	private BackupDAO	backupDAO;
	private PostgresDAO	postgresDAO;
	
	public BackupBO() {
		try {
			backupDAO = new BackupDAO();
			postgresDAO = new PostgresDAO();
		}
		catch (Exception erro) {
			System.out.println("Error construtor BackupBO " + erro.getMessage());
		}
	}
	
	public String consistsDataBackup(DataBackupTO dataBackupTO) {
		File pathPGDump = new File(dataBackupTO.getPgDump());
		
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
		if (dataBackupTO.getNameBackup().equals("") || dataBackupTO.getNameBackup() == null) {
			return "Input Time Backup empty!";
		}
		if (!pathPGDump.exists()) {
			return "The path of pg_dump could not be found!";
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
	
	public DataBackupTO consistsReadProperties() throws Exception {
		boolean returnExistFileProperties = existFileProperties();
		if (returnExistFileProperties) {
			BackupDAO backupDAO = new BackupDAO();
			DataBackupTO dataBackupTO = backupDAO.readProperties();
			return dataBackupTO;
		}
		return null;
	}
	
	public boolean existFileProperties() {
		File file = ConectionDAO.getProperties();
		if (file.exists()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String consistsBackupPostgres(DataBackupTO dataBackupTO) {
		String returnConnectBackupPostgres = postgresDAO.connectBackupPostgres(dataBackupTO);
		if (returnConnectBackupPostgres.equalsIgnoreCase("ok")) {
			return returnConnectBackupPostgres;
		}
		return returnConnectBackupPostgres;
	}
}