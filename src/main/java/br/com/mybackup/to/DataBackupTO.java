package br.com.mybackup.to;

import java.io.Serializable;

public class DataBackupTO implements Serializable {
	private static final long	serialVersionUID	= 2149773880652369056L;
	
	private String				pgDump;
	private String				hostName;
	private String				portComunication;
	private String				database;
	private String				user;
	private String				password;
	
	private String				locationBackup;
	private String				timeBackup;
	private String				nameBackup;
	
	private String				clientGoogleDrive;
	private String				idGoogleDrive;
	private String				folderGoogleDrive;
	
	public String getPgDump() {
		return pgDump;
	}
	
	public void setPgDump(String pgDump) {
		this.pgDump = pgDump;
	}
	
	public String getHostName() {
		return hostName;
	}
	
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	public String getPortComunication() {
		return portComunication;
	}
	
	public void setPortComunication(String portComunication) {
		this.portComunication = portComunication;
	}
	
	public String getDatabase() {
		return database;
	}
	
	public void setDatabase(String database) {
		this.database = database;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLocationBackup() {
		return locationBackup;
	}
	
	public void setLocationBackup(String locationBackup) {
		this.locationBackup = locationBackup;
	}
	
	public String getTimeBackup() {
		return timeBackup;
	}
	
	public void setTimeBackup(String timeBackup) {
		this.timeBackup = timeBackup;
	}
	
	public String getNameBackup() {
		return nameBackup;
	}
	
	public void setNameBackup(String nameBackup) {
		this.nameBackup = nameBackup;
	}
	
	public String getClientGoogleDrive() {
		return clientGoogleDrive;
	}
	
	public void setClientGoogleDrive(String clientGoogleDrive) {
		this.clientGoogleDrive = clientGoogleDrive;
	}
	
	public String getIdGoogleDrive() {
		return idGoogleDrive;
	}
	
	public void setIdGoogleDrive(String idGoogleDrive) {
		this.idGoogleDrive = idGoogleDrive;
	}
	
	public String getFolderGoogleDrive() {
		return folderGoogleDrive;
	}
	
	public void setFolderGoogleDrive(String folderGoogleDrive) {
		this.folderGoogleDrive = folderGoogleDrive;
	}
	
}
