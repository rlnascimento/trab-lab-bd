package labbd.series.model;

import java.util.Calendar;

public class RelatorioSerie {

	private String codigoSerie;
	private String nomeSerie;
	private String descricaoSerie;
	private String operacao;
	private Calendar dataOperacao;

	public String getCodigoSerie() {
		return codigoSerie;
	}

	public void setCodigoSerie(String codigoSerie) {
		this.codigoSerie = codigoSerie;
	}

	public String getNomeSerie() {
		return nomeSerie;
	}

	public void setNomeSerie(String nomeSerie) {
		this.nomeSerie = nomeSerie;
	}

	public String getDescricaoSerie() {
		return descricaoSerie;
	}

	public void setDescricaoSerie(String descricaoSerie) {
		this.descricaoSerie = descricaoSerie;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public Calendar getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(Calendar dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

}
