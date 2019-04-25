package br.com.mybackup.to;

import java.io.Serializable;

public class ArquivoTO implements Serializable {

	private static final long serialVersionUID = 7492711736085977373L;

	private String nomeArquivo;
	private Integer tamanhoArquivo;
	private String LocalArquivos;

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Integer getTamanhoArquivo() {
		return tamanhoArquivo;
	}

	public void setTamanhoArquivo(Integer tamanhoArquivo) {
		this.tamanhoArquivo = tamanhoArquivo;
	}

	public String getLocalArquivos() {
		return LocalArquivos;
	}

	public void setLocalArquivos(String localArquivos) {
		LocalArquivos = localArquivos;
	}

}
