package br.com.mybackup.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import br.com.mybackup.to.DataBackupTO;
import br.com.mybackup.util.BackupProperties;

public class BackupDAO {
	
	private static String	PGDUMP				= "postgres.pgDump";
	private static String	HOST_NAME			= "postgres.hostName";
	private static String	PORT_COMUNICATION	= "postgres.portComunication";
	private static String	DATABASE			= "postgres.database";
	private static String	USER				= "postgres.user";
	private static String	PASSWORD			= "postgres.password";
	private static String	LOCATION_BACKUP		= "backup.locationBackup";
	private static String	TIME_BACKUP			= "backup.timeBackup";
	private static String	NAME_BACKUP			= "backup.nameBackup";
	private static String	CLIENT_GOOGLEDRIVE	= "drive.clientGoogleDrive";
	private static String	ID_GOOGLEDRIVE		= "drive.idGoogleDrive";
	private static String	FOLDER_GOOGLEDRIVE	= "drive.folderGoogleDrive";
	
	File					fileProperties		= null;
	
	public BackupDAO() throws Exception {
		fileProperties = ConectionDAO.getProperties();
	}
	
	public String createProperties(DataBackupTO backupTO) {
		BackupProperties properties = new BackupProperties();
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
			properties.setProperty(NAME_BACKUP, backupTO.getNameBackup());
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
	
	public DataBackupTO readProperties() {
		DataBackupTO dataBackupTO = new DataBackupTO();
		try {
			BackupProperties properties = new BackupProperties();
			FileInputStream fileInputStream = null;
			fileInputStream = new FileInputStream(fileProperties);
			properties.load(fileInputStream);
			
			dataBackupTO.setPgDump(properties.getProperty(PGDUMP));
			dataBackupTO.setHostName(properties.getProperty(HOST_NAME));
			dataBackupTO.setPortComunication(properties.getProperty(PORT_COMUNICATION));
			dataBackupTO.setDatabase(properties.getProperty(DATABASE));
			dataBackupTO.setUser(properties.getProperty(USER));
			dataBackupTO.setPassword(properties.getProperty(PASSWORD));
			dataBackupTO.setLocationBackup(properties.getProperty(LOCATION_BACKUP));
			dataBackupTO.setTimeBackup(properties.getProperty(TIME_BACKUP));
			dataBackupTO.setNameBackup(properties.getProperty(NAME_BACKUP));
			dataBackupTO.setClientGoogleDrive(properties.getProperty(CLIENT_GOOGLEDRIVE));
			dataBackupTO.setIdGoogleDrive(properties.getProperty(ID_GOOGLEDRIVE));
			dataBackupTO.setFolderGoogleDrive(properties.getProperty(FOLDER_GOOGLEDRIVE));
			
		}
		catch (Exception erro) {
			System.out.println("Erro no método readProperties " + erro.getMessage());
		}
		return dataBackupTO;
	}
}
