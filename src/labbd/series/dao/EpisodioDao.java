package labbd.series.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import labbd.series.model.Episodio;

public class EpisodioDao {

	private Connection connection;
	private String query;

	public EpisodioDao(Connection connection) {
		this.connection = connection;
	}

	public boolean salvar(Episodio episodio) throws SQLException {
		boolean isInsert = false;
		
		if (consultar(episodio.getNumero(), episodio.getTemporada(), episodio.getCodigoSerie()) == null) {
			query = "{CALL USP_INSERIR_EPISODIO(?, ?, ?, ?, ?, ?)}";
			isInsert = true;
		} else {
			query = "{CALL USP_ALTERAR_EPISODIO(?, ?, ?, ?, ?, ?)}";
		}

		try (CallableStatement ctm = connection.prepareCall(query)) {
			ctm.setInt(1, episodio.getNumero());
			ctm.setInt(2, episodio.getTemporada());
			ctm.setString(3, episodio.getCodigoSerie());
			ctm.setDate(4, new java.sql.Date(episodio.getDataExibicao().getTimeInMillis()));
			ctm.setString(5, episodio.getDuracao());
			ctm.setString(6, episodio.getDescricao());

			ctm.executeUpdate();
		}
		
		return isInsert;
	}

	public Episodio consultar(int numero, int temporada, String codigoSerie) throws SQLException {
		Episodio episodio = null;
		query = "SELECT NUMERO, TEMPORADA, CODIGO_SERIE, DATA_EXIBICAO, DURACAO, DESCRICAO_EPISODIO FROM EPISODIO WHERE (NUMERO = ?) AND (TEMPORADA = ?) AND (CODIGO_SERIE = ?)";

		try (PreparedStatement stm = connection.prepareStatement(query)) {
			stm.setInt(1, numero);
			stm.setInt(2, temporada);
			stm.setString(3, codigoSerie);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				episodio = new Episodio();

				episodio.setNumero(rs.getInt("NUMERO"));
				episodio.setTemporada(rs.getInt("TEMPORADA"));
				episodio.setCodigoSerie(rs.getString("CODIGO_SERIE"));
				episodio.setDataExibicao(dateToCalendar(rs.getDate("DATA_EXIBICAO")));
				episodio.setDuracao(rs.getString("DURACAO"));
				episodio.setDescricao(rs.getString("DESCRICAO_EPISODIO"));
			}
		}

		return episodio;
	}

	public void excluir(int numero, int temporada, String codigoSerie) throws SQLException {
		query = "{CALL USP_EXCLUIR_EPISODIO(?, ?, ?)}";

		try (CallableStatement ctm = connection.prepareCall(query)) {
			ctm.setInt(1, numero);
			ctm.setInt(2, temporada);
			ctm.setString(3, codigoSerie);

			ctm.executeUpdate();
		}
	}

	public List<Episodio> listar() throws SQLException {
		List<Episodio> episodios = new ArrayList<>();
		query = "SELECT NUMERO, TEMPORADA, CODIGO_SERIE, DATA_EXIBICAO, DURACAO, DESCRICAO_EPISODIO FROM EPISODIO";

		try (PreparedStatement stm = connection.prepareStatement(query)) {
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Episodio episodio = new Episodio();

				episodio.setNumero(rs.getInt("NUMERO"));
				episodio.setTemporada(rs.getInt("TEMPORADA"));
				episodio.setCodigoSerie(rs.getString("CODIGO_SERIE"));
				episodio.setDataExibicao(dateToCalendar(rs.getDate("DATA_EXIBICAO")));
				episodio.setDuracao(rs.getString("DURACAO"));
				episodio.setDescricao(rs.getString("DESCRICAO_EPISODIO"));

				episodios.add(episodio);
			}
		}

		return episodios;
	}

	private Calendar dateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal;
	}
}
