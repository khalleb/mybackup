package br.com.mybackup.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.com.mybackup.to.DataBackupTO;
import br.com.mybackup.util.UTIL;

// C:/Program Files/PostgreSQL/9.3/bin\pg_dump.exe
//--host localhost
//--port 5432
//--username "postgres"
//--no-password 
//--format custom
//--blobs
//--verbose
//--file "D:\backupPostgres\nameBD.backup"
//"dws"

public class PostgresDAO {
	// PARAMETROS DO POSTGRES
	private static String	HOST				= "-h";
	private static String	PORT				= "-p";
	private static String	USER				= "-U";
	private static String	FORMAT				= "-F";
	private static String	CLEAN				= "c";
	private static String	BLOBS				= "-b";
	private static String	PROGRESS			= "-v";
	private static String	NAME_FILE			= "-f";
	
	private static String	EXTENSION_BACKUP	= ".backup";
	
	public String connectBackupPostgres(DataBackupTO dataBackupTO) {
		String returnLinePostgres = null;
		try {
			final List<String> commands = new ArrayList<String>();
			
			// CRIA O PARAMETRO DO POSTEGRES DENTRO DE UMA LISTA
			commands.add(dataBackupTO.getPgDump());
			commands.add(HOST);
			commands.add(dataBackupTO.getHostName());
			commands.add(PORT);
			commands.add(dataBackupTO.getPortaComunicacao());
			commands.add(USER);
			commands.add(dataBackupTO.getUsuario());
			commands.add(FORMAT);
			commands.add(CLEAN);
			commands.add(BLOBS);
			commands.add(PROGRESS);
			commands.add(NAME_FILE);
			commands.add(nameFileBackup(dataBackupTO));
			commands.add(dataBackupTO.getNomeDatabase());
			
			// CRIA O PROCESSO DE BACKUP
			ProcessBuilder processBuilder = new ProcessBuilder(commands);
			processBuilder.environment().put("PGPASSWORD", dataBackupTO.getSenha());
			final Process process = processBuilder.start();
			
			final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String line = bufferedReader.readLine();
			
			int contLine = 0;
			while (line != null) {
				System.out.println(line);
				returnLinePostgres = line;
				line = bufferedReader.readLine();
				contLine = contLine + 1;
			}
			// VERIFICA A QUANTIDADE DE LINHAS QUE É VISUALIZADA NO PROCESSO DE BACKUP,
			// MAS SE RETORNA MENOS DE 15 LINHAS, ENTÃO
			// RETORNA A LINHA DO ERRO
			if (contLine < 15) {
				deleteBackupPostegres(dataBackupTO);
				return returnLinePostgres;
			}
			bufferedReader.close();
			process.waitFor();
			process.destroy();
			System.out.println("Backup feito com sucesso");
			
		}
		catch (Exception erro) {
			System.out.println("Erro no métodoconnectBackupPostgres" + erro.getMessage());
		}
		return "ok";
	}
	
	// NOME DO BACKUP
	// EX.: dws_2017-08-18_21-51.backup
	public String nameFileBackup(DataBackupTO dataBackupTO) {
		UTIL util = new UTIL();
		String separador = null;
		//LINUX
		if(System.getProperties().get("file.separator").equals("/")) {
			separador = "/";
		}else { // WINDOWS
			separador = "\\";
		}
		String nameFile = dataBackupTO.getLocalBackup() + separador + dataBackupTO.getNomeArquivoBackup() + "_" + util.getDateTime() + EXTENSION_BACKUP;
		return nameFile;
	}
	
	public void deleteBackupPostegres(DataBackupTO dataBackupTO) {
		File file = new File(nameFileBackup(dataBackupTO));
		if (file.exists()) {
			file.delete();
		}
	}
}
