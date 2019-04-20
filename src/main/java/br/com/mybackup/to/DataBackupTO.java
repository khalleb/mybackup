package br.com.mybackup.to;

import java.io.Serializable;

public class DataBackupTO implements Serializable {
	private static final long	serialVersionUID	= 2149773880652369056L;
	
	private String				pgDump;
	private String				hostName;
	private String				portaComunicacao;
	private String				nomeDatabase;
	private String				usuario;
	private String				senha;
	
	private String				localBackup;
	private String				horarioBackup;
	private String				nomeArquivoBackup;
	
	
	public String getPgDump() {
		return pgDump;
	}
	public void setPgDump(String pgDump) {
		this.pgDump = pgDump;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getPortaComunicacao() {
		return portaComunicacao;
	}
	public void setPortaComunicacao(String portaComunicacao) {
		this.portaComunicacao = portaComunicacao;
	}
	public String getNomeDatabase() {
		return nomeDatabase;
	}
	public void setNomeDatabase(String nomeDatabase) {
		this.nomeDatabase = nomeDatabase;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
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
	public String getNomeArquivoBackup() {
		return nomeArquivoBackup;
	}
	public void setNomeArquivoBackup(String nomeArquivoBackup) {
		this.nomeArquivoBackup = nomeArquivoBackup;
	}
	
}
