package br.com.mybackup.bo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import br.com.mybackup.dao.BackupDAO;
import br.com.mybackup.dao.ConectionDAO;
import br.com.mybackup.dao.PostgresDAO;
import br.com.mybackup.to.DataBackupTO;
import br.com.mybackup.util.UTIL;

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
	
	public String consistirDadosBackup(DataBackupTO dataBackupTO, List<DataBackupTO> listaDataBackupTO) {
		File pathPGDump = new File(dataBackupTO.getPgDump());
		
		if (dataBackupTO.getPgDump().isEmpty() || dataBackupTO.getPgDump() == null) {
			return "Caminho do Pg Dump obrigatório!";
		}
		if (!pathPGDump.exists()) {
			return "O pg_dump.exe não foi encontrado no caminho especificado!";
		}
		if (dataBackupTO.getHostName().isEmpty() || dataBackupTO.getHostName() == null) {
			return "HostName obrigatório!";
		}
		if (dataBackupTO.getPortaComunicacao().isEmpty() || dataBackupTO.getPortaComunicacao() == null) {
			return "Porta de comunicação obrigatória!";
		}
		if (dataBackupTO.getNomeDatabase().isEmpty() || dataBackupTO.getNomeDatabase() == null) {
			return "Nome do database obrigatório!";
		}
		if (dataBackupTO.getUsuario().isEmpty() || dataBackupTO.getUsuario() == null) {
			return "Usuário obrigatório!";
		}
		if (dataBackupTO.getSenha().isEmpty() || dataBackupTO.getSenha() == null) {
			return "Senha obrigatória!";
		}
		if (dataBackupTO.getLocalBackup().isEmpty() || dataBackupTO.getLocalBackup() == null) {
			return "Local do backup obrigatório!";
		}
		if (dataBackupTO.getHorarioBackup().isEmpty() || dataBackupTO.getHorarioBackup() == null) {
			return "Horário do backup obrigatório!";
		}
		if (dataBackupTO.getNomeArquivoBackup().isEmpty() || dataBackupTO.getNomeArquivoBackup() == null) {
			return "Nome do backup obrigatório!";
		}
		String retorno = verificarExistenciaCadastro(dataBackupTO, listaDataBackupTO);
		if(!retorno.equalsIgnoreCase("ok")) {
			return retorno;
		}
		return "ok";
	}
	
	private String verificarExistenciaCadastro(DataBackupTO dataBackupTO, List<DataBackupTO> listaDataBackupTO) {
		if(listaDataBackupTO != null && !listaDataBackupTO.isEmpty()) {
			for(DataBackupTO item : listaDataBackupTO) {
				if(item.getNomeDatabase().trim().equalsIgnoreCase(dataBackupTO.getNomeDatabase().trim())) {
					if(item.getHorarioBackup().trim().equalsIgnoreCase(dataBackupTO.getHorarioBackup().trim())) {
						return "Já existe um cadastro com o nome do banco de dados " + item.getNomeDatabase() + " e o horário do backup para as " + item.getHorarioBackup();
					}
				}
			}
		}
		return "ok";
	}
	
	public String salvar(List<DataBackupTO> dataBackupTO) {
		return backupDAO.salvar(dataBackupTO);
	}
	
	public List<DataBackupTO> lerArquivoDePropriedades() throws Exception {
		boolean verificar = existeArquivoConfiguracao();
		List<DataBackupTO> dataBackupTO = new ArrayList<DataBackupTO>();
		if (verificar) {
			BackupDAO backupDAO = new BackupDAO();
			dataBackupTO = backupDAO.lerArquivoJson();
		}
		return dataBackupTO;
	}
	
	public boolean existeArquivoConfiguracao() {
		File file = ConectionDAO.getProperties();
		if (file.exists()) {
			return true;
		}
		else {
			System.out.println("Não foi possível obter o arquivo de configuração.");
			return false;
		}
	}
	
	public String fazerBackup(DataBackupTO dataBackupTO) {
		return  postgresDAO.connectBackupPostgres(dataBackupTO);
	}
	
	public void consistBackupScheduled() {
		try {
			List<DataBackupTO> dataBackupTO = lerArquivoDePropriedades();
			if (dataBackupTO != null && !dataBackupTO.isEmpty()) {
				agendarTarefa(dataBackupTO);
			}
			else {
				System.out.println("Arquivo de configuração não encontrado para fazer o backup agendado!");
			}
		}
		catch (Exception erro) {
			System.out.println("Erro no método consistBackupScheduled " + erro.getMessage());
		}
	}
	
	public void agendarTarefa(List<DataBackupTO> dataBackupTO) {
		if(dataBackupTO != null && !dataBackupTO.isEmpty()) {
			for(DataBackupTO item : dataBackupTO) {
				agendarBackup(item);	
			}
		}
	}
	
	public void desagendarBackup(List<DataBackupTO> dataBackupTO) {
		if(dataBackupTO != null && !dataBackupTO.isEmpty()) {
			for(DataBackupTO item : dataBackupTO) {
				try {
					desagendarTarefa(item);	
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
	
	// BACKUP AGENDADO.
	public void agendarBackup(DataBackupTO dataBackupTO) {
		try {
			JobDetail job = JobBuilder.newJob(BackupScheduledBO.class)
					.withIdentity("job" + dataBackupTO, "group" + dataBackupTO).build();

			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("cronTrigger" + dataBackupTO, "group" + dataBackupTO)
					.withSchedule(CronScheduleBuilder.cronSchedule(cronBackupAutomatic(dataBackupTO))).build();

			Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
			scheduler1.start();
			scheduler1.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public String cronBackupAutomatic(DataBackupTO dataBackupTO) {
		// "0 49 09 * * ?" MODELO CRON QUE É OBRIGÁTÓRIO PARA USO DA LIB QUARTZ
		// SEGUNDO, MINUTO, HORA
		UTIL util = new UTIL();
		String cron = "0 " + util.getMinuteView(dataBackupTO) + " " + util.getHourView(dataBackupTO) + " * * ?";
		return cron;
	}
	
	public void desagendarTarefa(DataBackupTO dataBackupTO) throws InterruptedException {
		try {
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();
			JobDetail jobDetail = JobBuilder.newJob(BackupScheduledBO.class).withIdentity("job" + dataBackupTO, "group" + dataBackupTO).build();
			
			Thread.sleep(6);
			scheduler.interrupt(jobDetail.getKey());
			Thread.sleep(2 * 1000L);
			TriggerKey triggerKey = TriggerKey.triggerKey("cronTrigger" + dataBackupTO, "group" + dataBackupTO);
			scheduler.unscheduleJob(triggerKey);
			scheduler.interrupt(jobDetail.getKey());
			scheduler.interrupt("job" + dataBackupTO);
			scheduler.deleteJob(jobDetail.getKey());
			scheduler.shutdown();
			scheduler.shutdown(false);
		}
		catch (SchedulerException erro) {
			erro.printStackTrace();
		}
	}
}
