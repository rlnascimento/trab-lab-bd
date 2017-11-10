package labbd.series.controller;

import java.sql.SQLException;
import java.util.List;

import labbd.series.dao.Oracle;
import labbd.series.dao.RelatorioEpisodioDao;
import labbd.series.model.RelatorioEpisodio;

public class RelatorioEpisodioController {

	private RelatorioEpisodioDao relatorioEpisodioDao = new RelatorioEpisodioDao(Oracle.getConnection());

	public List<RelatorioEpisodio> getRelatorio() throws SQLException {
		try {
			return relatorioEpisodioDao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
