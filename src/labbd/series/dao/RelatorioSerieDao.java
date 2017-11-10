package labbd.series.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import labbd.series.model.RelatorioSerie;

public class RelatorioSerieDao {

	private Connection connection;
	private String query;

	public RelatorioSerieDao(Connection connection) {
		this.connection = connection;
	}

	public List<RelatorioSerie> listar() throws SQLException {
		List<RelatorioSerie> relatorio = new ArrayList<>();
		query = "SELECT CODIGO_SERIE, NOME_SERIE, DESCRICAO_SERIE, OPERACAO, DATA_OPERACAO FROM SERIE_RELATORIO";

		try (PreparedStatement stm = connection.prepareStatement(query)) {
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				RelatorioSerie relatorioSerie = new RelatorioSerie();

				relatorioSerie.setCodigoSerie(rs.getString("CODIGO_SERIE"));
				relatorioSerie.setNomeSerie(rs.getString("NOME_SERIE"));
				relatorioSerie.setDescricaoSerie(rs.getString("DESCRICAO_SERIE"));
				relatorioSerie.setOperacao(getOperacao(rs.getString("OPERACAO")));
				relatorioSerie.setDataOperacao(dateToCalendar(rs.getTimestamp("DATA_OPERACAO")));

				relatorio.add(relatorioSerie);
			}
		}

		return relatorio;
	}

	public Calendar dateToCalendar(Timestamp date) throws SQLException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal;
	}

	private String getOperacao(String operacao) {
		Map<String, String> op = new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;

			{
				put("I", "Inserção");
				put("U", "Alteração");
				put("D", "Exclusão");
			}
		};

		return op.get(operacao);
	}
}
