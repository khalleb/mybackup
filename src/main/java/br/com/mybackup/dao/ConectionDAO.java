package br.com.mybackup.dao;

import java.io.File;

/**
 * AS A PATTERN THE ARQUIVO PROPERTIES WILL BE CREATED WITHIN THE CONTAINER (TOMCAT).
 */
public class ConectionDAO {
	// NOME DO ARQUIVO DE CONFIGURAÇÃO JSON
	private static String	FILE_PROPERTIES	= "backup-config.json";
	
	public static File getProperties() {
		File file = new File(FILE_PROPERTIES);
		try {
			return file;
		}
		catch (Exception erro) {
			System.out.println("Erro na classe ConectionDAO " + erro.getMessage());
		}
		return file;
	}
}
