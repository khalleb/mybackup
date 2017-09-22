package br.com.mybackup.ctrl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mybackup.bo.BackupBO;
import br.com.mybackup.to.DataBackupTO;

@ManagedBean
@SessionScoped
public class BackupCTRL {
	
	DataBackupTO	dataBackupTO;
	
	public BackupCTRL() {
		try {
			dataBackupTO = new DataBackupTO();
		}
		catch (Exception erro) {
			System.out.println("Erro no construtor BackupCTRL " + erro.getMessage());
		}
	}
	
	public DataBackupTO getDataBackupTO() {
		return dataBackupTO;
	}
	
	public void setDataBackupTO(DataBackupTO dataBackupTO) {
		this.dataBackupTO = dataBackupTO;
	}
	
	public String createProperties() {
		try {
			BackupBO backupBO = new BackupBO();
			String retunrConsistsCreateProperties = backupBO.consistsCreateProperties(dataBackupTO);
			if (retunrConsistsCreateProperties.equalsIgnoreCase("ok")) {
				System.out.println("Arquivo salvo com sucesso!");
			}
			else {
				return retunrConsistsCreateProperties;
			}
		}
		catch (Exception erro) {
			System.out.println("Erro no método createProperties " + erro.getMessage());
		}
		return "";
	}
	
}
