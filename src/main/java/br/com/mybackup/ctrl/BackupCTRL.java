package br.com.mybackup.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.mybackup.bo.BackupBO;
import br.com.mybackup.to.DataBackupTO;
import br.com.mybackup.util.Msg;

@ManagedBean
@SessionScoped
public class BackupCTRL {

	DataBackupTO dataBackupTO;
	List<DataBackupTO> listaDataBackupTO = new ArrayList<DataBackupTO>();
	BackupBO backupBO = new BackupBO();

	public DataBackupTO getDataBackupTO() {
		return dataBackupTO;
	}

	public void setDataBackupTO(DataBackupTO dataBackupTO) {
		this.dataBackupTO = dataBackupTO;
	}

	public List<DataBackupTO> getListaDataBackupTO() {
		return listaDataBackupTO;
	}

	public void setListaDataBackupTO(List<DataBackupTO> listaDataBackupTO) {
		this.listaDataBackupTO = listaDataBackupTO;
	}

	public BackupCTRL() {
		try {
			dataBackupTO = new DataBackupTO();
			lerArquivo();
		} catch (Exception erro) {
			System.out.println("Erro no construtor BackupCTRL " + erro.getMessage());
		}
	}

	public void lerArquivo() throws Exception {
		List<DataBackupTO> lista = backupBO.lerArquivoDePropriedades();
		this.listaDataBackupTO = lista;
	}

	public String salvar() {
		try {
			String retorno = backupBO.salvar(this.listaDataBackupTO);
			if (retorno.equalsIgnoreCase("ok")) {
				backupBO.desagendarBackup(this.listaDataBackupTO);
				backupBO.agendarTarefa(this.listaDataBackupTO);
				Msg.info(Msg.msgGetProperties("retorno.msg.sucesso.salvar.properties"));
			} else {
				System.out.println(retorno);
				Msg.info(retorno);
				return retorno;
			}
		} catch (Exception erro) {
			System.out.println("Erro no método createProperties " + erro.getMessage());
		}
		return "";
	}

	public void adicionarLista() {
		String retorno = consitirDados();
		if (retorno.equalsIgnoreCase("ok")) {
			this.listaDataBackupTO.add(this.dataBackupTO);
			this.dataBackupTO = new DataBackupTO();
		} else {
			Msg.warn(retorno);
		}
	}

	public void editarItemLista(DataBackupTO entidade) {
		if (entidade != null) {
			this.dataBackupTO = entidade;
			removerItemLista(entidade);
		}
	}

	private String consitirDados() {
		String retorno = this.backupBO.consistirDadosBackup(this.dataBackupTO, this.listaDataBackupTO);
		if (retorno.equalsIgnoreCase("ok")) {
			return retorno;
		}
		return retorno;
	}

	public void removerItemLista(DataBackupTO entidade) {
		this.listaDataBackupTO.remove(entidade);
	}

	public void backup() {
		for (DataBackupTO item : this.listaDataBackupTO) {

			String returnConsistsBackupPostgres = backupBO.fazerBackup(item);
			if (returnConsistsBackupPostgres.equalsIgnoreCase("ok")) {
				Msg.info(Msg.msgGetProperties("retorno.msg.sucesso.do.backup"));
			} else {
				Msg.error(returnConsistsBackupPostgres);
			}
			// COMO TESTE, IRÁ FAZER SOMENTE UM BACKUP, NO CASO O PRIMEIRO DA LISTA
			break;
		}
	}
}
