package br.com.mybackup.bo;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.mybackup.to.DataBackupTO;
import br.com.mybackup.util.UTIL;

public class BackupScheduledBO implements Job {
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		try {
			BackupBO backupBO = new BackupBO();
			List<DataBackupTO> dataBackupTO = backupBO.lerArquivoDePropriedades();
			if(dataBackupTO != null && !dataBackupTO.isEmpty()) {
				List<DataBackupTO> aux = new ArrayList<DataBackupTO>();
				//Recupera a entidade que contem a informação necessária para fazer o backup e armazena em uma auxliar
				for(DataBackupTO item : dataBackupTO) {
					if(item.getHorarioBackup().equalsIgnoreCase(UTIL.obterHorarioAtual())) {
						aux.add(item);
					}
				}
				for(DataBackupTO item : aux) {
					backupBO.fazerBackup(item);	
				}
			}
		}
		catch (Exception erro) {
			System.out.println("Erro na classe BackupScheduledBO " + erro.getMessage());
			erro.printStackTrace();
		}
		
	}
	
}
