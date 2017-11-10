package labbd.series.controller;

import java.sql.SQLException;
import java.util.List;

import labbd.series.dao.Oracle;
import labbd.series.dao.RelatorioSerieDao;
import labbd.series.model.RelatorioSerie;

public class RelatorioSerieController {

	private RelatorioSerieDao relatorioSerieDao = new RelatorioSerieDao(Oracle.getConnection());

	public List<RelatorioSerie> getRelatorio() {
		try {
			return relatorioSerieDao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
