package labbd.series.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import labbd.series.model.Serie;

public class SerieDao {

	private Connection connection;
	private String query;

	public SerieDao(Connection connection) {
		this.connection = connection;
	}

	public boolean salvar(Serie serie) throws SQLException {
		boolean isInsert = false;
		
		if (consultar(serie.getCodigoSerie()) == null) {
			query = "{CALL USP_INSERIR_SERIE(?, ?, ?)}";
			isInsert = true;
		} else {
			query = "{CALL USP_ALTERAR_SERIE(?, ?, ?)}";
		}

		try (CallableStatement ctm = connection.prepareCall(query)) {
			ctm.setString(1, serie.getCodigoSerie());
			ctm.setString(2, serie.getNomeSerie());
			ctm.setString(3, serie.getDescricaoSerie());

			ctm.executeUpdate();
		}
		
		return isInsert;
	}

	public Serie consultar(String codigo) throws SQLException {
		Serie serie = null;
		query = "SELECT CODIGO_SERIE, NOME_SERIE, DESCRICAO_SERIE FROM SERIE WHERE CODIGO_SERIE = ?";

		try (PreparedStatement stm = connection.prepareStatement(query)) {
			stm.setString(1, codigo);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				serie = new Serie();

				serie.setCodigoSerie(rs.getString("CODIGO_SERIE"));
				serie.setNomeSerie(rs.getString("NOME_SERIE"));
				serie.setDescricaoSerie(rs.getString("DESCRICAO_SERIE"));
			}
		}

		return serie;
	}

	public void excluir(String codigo) throws SQLException {
		query = "{CALL USP_EXCLUIR_SERIE(?)}";

		try (CallableStatement ctm = connection.prepareCall(query)) {
			ctm.setString(1, codigo);

			ctm.executeUpdate();
		}
	}

	public List<Serie> listar() throws SQLException {
		List<Serie> series = new ArrayList<>();
		query = "SELECT CODIGO_SERIE, NOME_SERIE, DESCRICAO_SERIE FROM SERIE";

		try (PreparedStatement stm = connection.prepareStatement(query)) {
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Serie serie = new Serie();

				serie.setCodigoSerie(rs.getString("CODIGO_SERIE"));
				serie.setNomeSerie(rs.getString("NOME_SERIE"));
				serie.setDescricaoSerie(rs.getString("DESCRICAO_SERIE"));

				series.add(serie);
			}
		}

		return series;
	}
}
