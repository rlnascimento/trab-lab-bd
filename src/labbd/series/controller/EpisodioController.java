package labbd.series.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import labbd.series.dao.EpisodioDao;
import labbd.series.dao.Oracle;
import labbd.series.model.Episodio;

@WebServlet("/EpisodioController")
public class EpisodioController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private EpisodioDao episodioDao = new EpisodioDao(Oracle.getConnection());
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
		Episodio episodio = new Episodio();

		episodio.setNumero(Integer.parseInt(req.getParameter("numero-episodio")));
		episodio.setTemporada(Integer.parseInt(req.getParameter("temporada-episodio")));
		episodio.setCodigoSerie(req.getParameter("codigo-serie-temporada"));
		episodio.setDataExibicao(stringToCalendar(req.getParameter("exibicao-episodio")));
		episodio.setDuracao(req.getParameter("duracao-episodio"));
		episodio.setDescricao(req.getParameter("descricao-episodio"));

		try {
			if(episodioDao.salvar(episodio)) {
				feedback = "Episódio cadastrado com sucesso!";
			} else {
				feedback = "Episódio alterado com sucesso!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			feedback = "Ocorreram erros ao salvar!";
		}

		url = "/jsp/episodio/list.jsp";
	}

	private void editar(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Episodio episodio = episodioDao.consultar(Integer.parseInt(req.getParameter("episodio-numero-editar")),
					Integer.parseInt(req.getParameter("episodio-temporada-editar")), req.getParameter("episodio-codigo-editar"));

			req.setAttribute("episodio", episodio);
			req.setAttribute("readonly", "readonly");

			url = "/jsp/episodio/form.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			feedback = "Ocorreram erros ao editar!";
		}
	}

	private void excluir(HttpServletRequest req, HttpServletResponse resp) {
		try {
			episodioDao.excluir(Integer.parseInt(req.getParameter("episodio-numero-excluir")),
					Integer.parseInt(req.getParameter("episodio-temporada-excluir")),
					req.getParameter("episodio-codigo-excluir"));

			feedback = "Episódio excluido com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			feedback = "Ocorreram erros ao excluir!";
		}

		url = "/jsp/episodio/list.jsp";
	}

	public List<Episodio> getLista() {
		try {
			return episodioDao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Calendar stringToCalendar(String data) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(format.parse(data));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return calendar;
	}
}
