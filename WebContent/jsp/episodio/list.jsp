<jsp:useBean id="episodio" class="labbd.series.controller.EpisodioController" />
<jsp:useBean id="serie" class="labbd.series.controller.SerieController" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="../header.jsp" />

<!-- jQuery Library (skip this step if already called on page ) -->
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>

<h2 class="page_title text-center">
	<span>Lista de Episódios</span><br />
	<span class="sep"></span><br/>
</h2>
<div class="row">
	<div class="col-md-12">
		<label>Filtro de Episódios Por Série</label><br/>
		
		<select onchange="filtro(this, 'lista-episodios', '2')" class="form-control">
			<c:forEach var="serie" items="${serie.lista}">
				<option value="${serie.codigoSerie}">${serie.codigoSerie} - ${serie.nomeSerie}</option>
			</c:forEach>
		</select>
	</div>
	<div class="col-md-12">
		<h4 id="feedback" class="text-center" style="color: red;">${feedback}</h4>
		<table id="lista-episodios" class="dc_tables2_0" style="width: 100%; margin-top: 10px;">
			<tbody>
				<thead>
					<tr>
						<th class="bold">Número</th>
						<th class="bold">Temporada</th>
						<th class="bold">Código da Série</th>
						<th class="bold">Data de Exibição</th>
						<th class="bold">Duração</th>
						<th class="bold">Descrição</th>
						<th class="bold">Editar</th>
						<th class="bold">Excluir</th>
					</tr>
				</thead>
				<c:forEach var="episodio" items="${episodio.lista}">
				  <tr>
				    <th>${episodio.numero}</th>
				    <th>${episodio.temporada}</th>
				    <th>${episodio.codigoSerie}</th>
				    <th><fmt:formatDate pattern="dd/MM/yyyy" value="${episodio.dataExibicao.time}"/></th>
				    <th>${episodio.duracao}</th>
				    <th>${episodio.descricao}</th>
						<th>
							<form action="${pageContext.request.contextPath}/EpisodioController" method="post">
								<input type="text" name="episodio-numero-editar" value="${episodio.numero}" hidden />
								<input type="text" name="episodio-temporada-editar" value="${episodio.temporada}" hidden />
								<input type="text" name="episodio-codigo-editar" value="${episodio.codigoSerie}" hidden />
								<button id="segurado-editar" type="submit" name="command" value="editar" class="btn btn-primary">
									<span class="glyphicon glyphicon-pencil"></span> Editar
								</button>
							</form>
						</th>
						<th>
							<form action="${pageContext.request.contextPath}/EpisodioController" method="post">
								<input type="text" name="episodio-numero-excluir" value="${episodio.numero}" hidden />
								<input type="text" name="episodio-temporada-excluir" value="${episodio.temporada}" hidden />
								<input type="text" name="episodio-codigo-excluir" value="${episodio.codigoSerie}" hidden />
								<button id="segurado-excluir" type="submit" name="command" value="excluir" class="btn btn-danger">
									<span class="glyphicon glyphicon-trash"></span> Excluir
								</button>
							</form>
						</th>
				  </tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript">
	$(function() {
		$("table tr:nth-child(odd)").addClass("odd-row");
		$("table td:first-child, table th:first-child").addClass("first");
		$("table td:last-child, table th:last-child").addClass("last");
	});
</script>

<jsp:include page="../footer.jsp" />