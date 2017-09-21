package br.com.mybackup.bo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mybackup.to.DadosBackupTO;

@ManagedBean
@SessionScoped
public class BackupCTRL {
	
	DadosBackupTO	dadosBackupTO	= new DadosBackupTO();
	
	public DadosBackupTO getDadosBackupTO() {
		return dadosBackupTO;
	}
	
	public void setDadosBackupTO(DadosBackupTO dadosBackupTO) {
		this.dadosBackupTO = dadosBackupTO;
	}
	
}
