package br.com.mybackup.to;

import java.io.Serializable;
import java.util.List;

public class ArquivosBackupTO implements Serializable {

	private static final long serialVersionUID = -4804398021067078620L;

	private String localBackup;
	private String horarioBackup;
	private String nomePastaBackup;
	private List<ArquivoTO> arquivos;

	public String getLocalBackup() {
		return localBackup;
	}

	public void setLocalBackup(String localBackup) {
		this.localBackup = localBackup;
	}

	public String getHorarioBackup() {
		return horarioBackup;
	}

	public void setHorarioBackup(String horarioBackup) {
		this.horarioBackup = horarioBackup;
	}

	public String getNomePastaBackup() {
		return nomePastaBackup;
	}

	public void setNomePastaBackup(String nomePastaBackup) {
		this.nomePastaBackup = nomePastaBackup;
	}

	public List<ArquivoTO> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<ArquivoTO> arquivos) {
		this.arquivos = arquivos;
	}

}
