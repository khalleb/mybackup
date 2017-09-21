package br.com.mybackup.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import br.com.mybackup.to.DadosBackupTO;

public class BackupDAO {
	
	private static String	PGDUMP				= "pgDump";
	private static String	HOST_NAME			= "hostName";
	private static String	PORT_COMUNICATION	= "portComunication";
	private static String	DATABASE			= "database";
	private static String	USER				= "user";
	private static String	PASSWORD			= "password";
	private static String	LOCATION_BACKUP		= "locationBackup";
	private static String	TIME_BACKUP			= "timeBackup";
	private static String	CLIENT_GOOGLEDRIVE	= "clientGoogleDrive";
	private static String	ID_GOOGLEDRIVE		= "idGoogleDrive";
	private static String	FOLDER_GOOGLEDRIVE	= "folderGoogleDrive";
	
	File					fileProperties		= null;
	
	public BackupDAO() throws Exception {
		fileProperties = ConectionDAO.getProperties();
	}
	
	public String createProperties(DadosBackupTO backupTO) {
		Properties properties = new Properties();
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(fileProperties);
			
			// SET AS PROPERTY (KEY) AND ITS VALUES (VALUE)
			properties.setProperty(PGDUMP, backupTO.getPgDump());
			properties.setProperty(HOST_NAME, backupTO.getHostName());
			properties.setProperty(PORT_COMUNICATION, backupTO.getPortComunication());
			properties.setProperty(DATABASE, backupTO.getDatabase());
			properties.setProperty(USER, backupTO.getUser());
			properties.setProperty(PASSWORD, backupTO.getPassword());
			properties.setProperty(LOCATION_BACKUP, backupTO.getLocationBackup());
			properties.setProperty(TIME_BACKUP, backupTO.getTimeBackup());
			properties.setProperty(CLIENT_GOOGLEDRIVE, backupTO.getClientGoogleDrive());
			properties.setProperty(ID_GOOGLEDRIVE, backupTO.getIdGoogleDrive());
			properties.setProperty(FOLDER_GOOGLEDRIVE, backupTO.getFolderGoogleDrive());
			properties.store(outputStream, "Comments");
			outputStream.close();
		}
		catch (Exception erro) {
			System.out.println("Erro no método createProperties " + erro.getMessage());
		}
		return properties.toString();
	}
}
