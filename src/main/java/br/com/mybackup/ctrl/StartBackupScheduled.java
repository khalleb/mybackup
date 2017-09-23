package br.com.mybackup.ctrl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.mybackup.bo.BackupBO;
import br.com.mybackup.util.UTIL;

public class StartBackupScheduled implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			UTIL.versionSystem();
			BackupBO backupBO = new BackupBO();
			backupBO.consistBackupScheduled();
		}
		catch (Exception erro) {
			System.out.println("Erro na incialização do backup programado " + erro.getMessage());
			erro.getStackTrace();
		}
		
	}
	
}
