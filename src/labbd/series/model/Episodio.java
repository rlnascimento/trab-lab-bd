package labbd.series.model;

import java.util.Calendar;

public class Episodio {

	private int numero;
	private int temporada;
	private String codigoSerie;
	private Calendar dataExibicao;
	private String duracao;
	private String descricao;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getTemporada() {
		return temporada;
	}

	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}

	public String getCodigoSerie() {
		return codigoSerie;
	}

	public void setCodigoSerie(String codigoSerie) {
		this.codigoSerie = codigoSerie;
	}

	public Calendar getDataExibicao() {
		return dataExibicao;
	}

	public void setDataExibicao(Calendar dataExibicao) {
		this.dataExibicao = dataExibicao;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
