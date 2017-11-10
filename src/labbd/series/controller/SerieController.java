package labbd.series.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import labbd.series.dao.Oracle;
import labbd.series.dao.SerieDao;
import labbd.series.model.Serie;

@WebServlet("/SerieController")
public class SerieController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private SerieDao serieDao = new SerieDao(Oracle.getConnection());
	private String feedback = "";
	private String url = "";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String buttonSrc = req.getParameter("command");

		if (buttonSrc.equalsIgnoreCase("salvar")) {
			salvar(req, resp);
		} else if (buttonSrc.equalsIgnoreCase("editar")) {
			editar(req, resp);
		} else if (buttonSrc.equalsIgnoreCase("excluir")) {
			excluir(req, resp);
		}

		req.setAttribute("feedback", feedback);
		req.getRequestDispatcher(url).forward(req, resp);
	}

	private void salvar(HttpServletRequest req, HttpServletResponse resp) {
		Serie serie = new Serie();

		serie.setCodigoSerie(req.getParameter("codigo-serie"));
		serie.setNomeSerie(req.getParameter("nome-serie"));
		serie.setDescricaoSerie(req.getParameter("descricao-serie"));

		try {
			if(serieDao.salvar(serie)) {
				feedback = "Série cadastrada com sucesso!";
			} else {
				feedback = "Série alterada com sucesso!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			feedback = "Ocorreram erros ao salvar!";
		}

		url = "/jsp/serie/list.jsp";
	}

	private void editar(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Serie serie = serieDao.consultar(req.getParameter("serie-codigo-editar"));

			req.setAttribute("serie", serie);
			req.setAttribute("readonly", "readonly");
		} catch (Exception e) {
			e.printStackTrace();
			feedback = "Ocorreram erros ao editar!";
		}

		url = "/jsp/serie/form.jsp";
	}

	private void excluir(HttpServletRequest req, HttpServletResponse resp) {
		try {
			serieDao.excluir(req.getParameter("serie-codigo-excluir"));

			feedback = "Serie excluida com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			feedback = "Ocorreram erros ao excluir!";
		}

		url = "/jsp/serie/list.jsp";
	}

	public List<Serie> getLista() {
		try {
			return serieDao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
