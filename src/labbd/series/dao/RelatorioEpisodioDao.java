package labbd.series.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import labbd.series.model.RelatorioEpisodio;

public class RelatorioEpisodioDao {

	private Connection connection;
	private String query;

	public RelatorioEpisodioDao(Connection connection) {
		this.connection = connection;
	}

	public List<RelatorioEpisodio> listar() throws SQLException {
		List<RelatorioEpisodio> relatorio = new ArrayList<>();
		query = "SELECT NUMERO, TEMPORADA, CODIGO_SERIE, DATA_EXIBICAO, DURACAO, DESCRICAO_EPISODIO, OPERACAO, DATA_OPERACAO FROM EPISODIO_RELATORIO";

		try (PreparedStatement stm = connection.prepareStatement(query)) {
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				RelatorioEpisodio relatorioEpisodio = new RelatorioEpisodio();

				relatorioEpisodio.setNumero(Integer.parseInt(rs.getString("NUMERO")));
				relatorioEpisodio.setTemporada(Integer.parseInt(rs.getString("TEMPORADA")));
				relatorioEpisodio.setCodigoSerie(rs.getString("CODIGO_SERIE"));
				relatorioEpisodio.setDataExibicao(dateToCalendar(rs.getDate("DATA_EXIBICAO")));
				relatorioEpisodio.setDuracao(rs.getString("DURACAO"));
				relatorioEpisodio.setDescricao(rs.getString("DESCRICAO_EPISODIO"));
				relatorioEpisodio.setOperacao(getOperacao(rs.getString("OPERACAO")));
				relatorioEpisodio.setDataOperacao(dateToCalendar(rs.getTimestamp("DATA_OPERACAO")));

				relatorio.add(relatorioEpisodio);
			}
		}

		return relatorio;
	}

	public Calendar dateToCalendar(Timestamp date) throws SQLException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal;
	}

	public Calendar dateToCalendar(Date date) throws SQLException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal;
	}

	@SuppressWarnings("serial")
	private String getOperacao(String operacao) {
		Map<String, String> op = new HashMap<String, String>() {
			{
				put("I", "Inserção");
				put("U", "Alteração");
				put("D", "Exclusão");
			}
		};

		return op.get(operacao);
	}
}
