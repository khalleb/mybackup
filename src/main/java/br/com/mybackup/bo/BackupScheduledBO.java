package br.com.mybackup.bo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.mybackup.to.DataBackupTO;

public class BackupScheduledBO implements Job {
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			BackupBO backupBO = new BackupBO();
			DataBackupTO dataBackupTO = backupBO.consistsReadProperties();
			backupBO.consistsBackupPostgres(dataBackupTO);
		}
		catch (Exception erro) {
			System.out.println("Erro na classe BackupScheduledBO " + erro.getMessage());
			erro.printStackTrace();
		}
		
	}
	
}
