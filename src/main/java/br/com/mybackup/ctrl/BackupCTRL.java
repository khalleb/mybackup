package br.com.mybackup.ctrl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mybackup.bo.BackupBO;
import br.com.mybackup.to.DataBackupTO;
import br.com.mybackup.util.Msg;

@ManagedBean
@SessionScoped
public class BackupCTRL {
	
	DataBackupTO	dataBackupTO;
	BackupBO		backupBO	= new BackupBO();
	
	public DataBackupTO getDataBackupTO() {
		return dataBackupTO;
	}
	
	public void setDataBackupTO(DataBackupTO dataBackupTO) {
		this.dataBackupTO = dataBackupTO;
	}
	
	public BackupCTRL() {
		try {
			startReadCreatproperties();
		}
		catch (Exception erro) {
			System.out.println("Erro no construtor BackupCTRL " + erro.getMessage());
		}
	}
	
	public void startReadCreatproperties() throws Exception {
		if (backupBO.consistsReadProperties() == null) {
			dataBackupTO = new DataBackupTO();
		}
		else {
			dataBackupTO = setPropertiesBackupTO();
		}
	}
	
	public String createProperties() {
		try {
			String retunrConsistsCreateProperties = backupBO.consistsCreateProperties(dataBackupTO);
			if (retunrConsistsCreateProperties.equalsIgnoreCase("ok")) {
				backupBO.stopBackupScheduled();
				backupBO.backupScheduled(dataBackupTO);
				Msg.info(Msg.msgGetProperties("retorno.msg.sucesso.salvar.properties"));
			}
			else {
				System.out.println(retunrConsistsCreateProperties);
				Msg.info(retunrConsistsCreateProperties);
				return retunrConsistsCreateProperties;
			}
		}
		catch (Exception erro) {
			System.out.println("Erro no método createProperties " + erro.getMessage());
		}
		return "";
	}
	
	public DataBackupTO setPropertiesBackupTO() {
		try {
			return backupBO.consistsReadProperties();
		}
		catch (Exception erro) {
			System.out.println("Erro no método setPropertiesBackupTO " + erro.getMessage());
			return null;
		}
	}
	
	public void backup() {
		String returnConsistsBackupPostgres = backupBO.consistsBackupPostgres(dataBackupTO);
		if (returnConsistsBackupPostgres.equalsIgnoreCase("ok")) {
			Msg.info(Msg.msgGetProperties("retorno.msg.sucesso.do.backup"));
		}
		else {
			Msg.error(returnConsistsBackupPostgres);
		}
	}
}
