package br.com.mybackup.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import br.com.mybackup.to.DataBackupTO;
import br.com.mybackup.util.Msg;

public class BackupDAO {

	File fileProperties = null;

	public BackupDAO() throws Exception {
		fileProperties = ConectionDAO.getProperties();
	}

	public String salvar(List<DataBackupTO> backupTO) {
		try {
			JSONArray jsonArray = new JSONArray(backupTO);
			FileWriter file = new FileWriter(fileProperties);
			file.write(jsonArray.toString());
			file.flush();
			file.close();
		} catch (Exception e) {
			System.out.println("Erro no método salvar " + e.getMessage());
			return e.getMessage();
			
		}
		return "ok";
	}

	public List<DataBackupTO> lerArquivoJson() {
		List<DataBackupTO> lista = new ArrayList<DataBackupTO>();
		try {
			FileInputStream fileInputStream = new FileInputStream(fileProperties.getAbsoluteFile());
			if (fileInputStream != null) {
				Reader in = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
				JSONArray array = new JSONArray(new JSONTokener(in));
				for (int i = 0; i < array.length(); i++) {
					DataBackupTO backupTO = new DataBackupTO();
					JSONObject jsonobject = array.getJSONObject(i);
					backupTO.setPgDump(jsonobject.getString("pgDump"));
					backupTO.setHostName(jsonobject.getString("hostName"));
					backupTO.setPortaComunicacao(jsonobject.getString("portaComunicacao"));
					backupTO.setNomeDatabase(jsonobject.getString("nomeDatabase"));
					backupTO.setUsuario(jsonobject.getString("usuario"));
					backupTO.setSenha(jsonobject.getString("senha"));
					backupTO.setLocalBackup(jsonobject.getString("localBackup"));
					backupTO.setHorarioBackup(jsonobject.getString("horarioBackup"));
					backupTO.setNomeArquivoBackup(jsonobject.getString("nomeArquivoBackup"));
					lista.add(backupTO);
				}
			}
		} catch (Exception erro) {
			Msg.error("Erro ao ler o arquivo de configuração. " + erro.getMessage());
			return lista;
		}
		return lista;
	}
}
