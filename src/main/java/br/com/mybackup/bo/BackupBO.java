package br.com.mybackup.bo;

import java.io.File;

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
	
	public String consistsDataBackup(DataBackupTO dataBackupTO) {
		File pathPGDump = new File(dataBackupTO.getPgDump());
		
		if (dataBackupTO.getPgDump().equals("") || dataBackupTO.getPgDump() == null) {
			return "Input pgDump path is empty!";
		}
		if (dataBackupTO.getHostName().equals("") || dataBackupTO.getHostName() == null) {
			return "Input HostName empty!";
		}
		if (dataBackupTO.getPortComunication().equals("") || dataBackupTO.getPortComunication() == null) {
			return "Input Porta Comunication empty!";
		}
		if (dataBackupTO.getDatabase().equals("") || dataBackupTO.getDatabase() == null) {
			return "Input Database empty!";
		}
		if (dataBackupTO.getUser().equals("") || dataBackupTO.getUser() == null) {
			return "Input User empty!";
		}
		if (dataBackupTO.getPassword().equals("") || dataBackupTO.getPassword() == null) {
			return "Input Password empty!";
		}
		if (dataBackupTO.getLocationBackup().equals("") || dataBackupTO.getLocationBackup() == null) {
			return "Input Location Backup empty!";
		}
		if (dataBackupTO.getTimeBackup().equals("") || dataBackupTO.getTimeBackup() == null) {
			return "Input Time Backup empty!";
		}
		if (dataBackupTO.getNameBackup().equals("") || dataBackupTO.getNameBackup() == null) {
			return "Input Time Backup empty!";
		}
		if (!pathPGDump.exists()) {
			return "The path of pg_dump could not be found!";
		}
		return "ok";
	}
	
	public String consistsCreateProperties(DataBackupTO dataBackupTO) {
		String returnConsistsDataBackup = consistsDataBackup(dataBackupTO);
		if (returnConsistsDataBackup.equalsIgnoreCase("ok")) {
			backupDAO.createProperties(dataBackupTO);
			return returnConsistsDataBackup;
		}
		return returnConsistsDataBackup;
	}
	
	public DataBackupTO consistsReadProperties() throws Exception {
		boolean returnExistFileProperties = existFileProperties();
		if (returnExistFileProperties) {
			BackupDAO backupDAO = new BackupDAO();
			DataBackupTO dataBackupTO = backupDAO.readProperties();
			return dataBackupTO;
		}
		return null;
	}
	
	public boolean existFileProperties() {
		File file = ConectionDAO.getProperties();
		if (file.exists()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// VERIFICAR O RETORNO DO POSTGRES E FAZ O BACKUP.
	public String consistsBackupPostgres(DataBackupTO dataBackupTO) {
		String returnConnectBackupPostgres = postgresDAO.connectBackupPostgres(dataBackupTO); // BACKUP
		if (returnConnectBackupPostgres.equalsIgnoreCase("ok")) {
			return returnConnectBackupPostgres;
		}
		return returnConnectBackupPostgres;
	}
	
	public void consistBackupScheduled() {
		try {
			DataBackupTO dataBackupTO = consistsReadProperties();
			if (dataBackupTO != null) {
				backupScheduled(dataBackupTO);
			}
			else {
				System.out.println("Arquivo de configuração não encontrado para fazer o backup agendado!");
			}
			
		}
		catch (Exception erro) {
			System.out.println("Erro no método consistBackupScheduled " + erro.getMessage());
		}
	}
	
	// BACKUP AGENDADO.
	public void backupScheduled(DataBackupTO dataBackupTO) {
		try {
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.start();
			JobDetail jobDetail = JobBuilder.newJob(BackupScheduledBO.class).withIdentity("BackupScheduledBO", "group1").build();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule(cronBackupAutomatic(dataBackupTO))).build();
			scheduler.scheduleJob(jobDetail, trigger);
		}
		catch (Exception erro) {
			System.out.println("Erro no backupScheduled " + erro.getMessage());
		}
	}
	
	public String cronBackupAutomatic(DataBackupTO dataBackupTO) {
		// "0 49 09 * * ?" MODELO CRON QUE É OBRIGÁTÓRIO PARA USO DA LIB QUARTZ
		// SEGUNDO, MINUTO, HORA
		UTIL util = new UTIL();
		String cron = "0 " + util.getMinuteView(dataBackupTO) + " " + util.getHourView(dataBackupTO) + " * * ?";
		return cron;
	}
	
	public void stopBackupScheduled() throws InterruptedException {
		try {
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();
			JobDetail jobDetail = JobBuilder.newJob(BackupScheduledBO.class).withIdentity("BackupScheduledBO", "group1").build();
			
			Thread.sleep(6);
			scheduler.interrupt(jobDetail.getKey());
			Thread.sleep(2 * 1000L);
			TriggerKey triggerKey = TriggerKey.triggerKey("BackupScheduledBO", "group1");
			System.err.println("triggerKey " + triggerKey + " : " + jobDetail.getKey());
			scheduler.unscheduleJob(triggerKey);
			scheduler.interrupt(jobDetail.getKey());
			scheduler.interrupt("BackupScheduledBO");
			scheduler.deleteJob(jobDetail.getKey());
			scheduler.shutdown();
			scheduler.shutdown(false);
		}
		catch (SchedulerException erro) {
			// TODO Auto-generated catch block
			erro.printStackTrace();
		}
	}
	
}
