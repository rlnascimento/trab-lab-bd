<jsp:useBean id="serie" class="labbd.series.controller.SerieController" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../header.jsp" />

<!-- jQuery Library (skip this step if already called on page ) -->
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>

<h2 class="page_title text-center">
	<span>Lista de Séries</span><br />
	<span class="sep"></span><br/>
</h2>
<div class="row">
	<div class="col-md-12">
		<h4 id="feedback" class="text-center" style="color: red;">${feedback}</h4>
		<table class="dc_tables2_0" style="width: 100%; margin-top: 10px;">
			<tbody>
				<thead>
					<tr>
						<th class="bold">Código</th>
						<th class="bold">Nome</th>
						<th class="bold">Descrição</th>
						<th class="bold">Editar</th>
						<th class="bold">Excluir</th>
					</tr>
				</thead>
				<c:forEach var="serie" items="${serie.lista}">
				  <tr>
				    <th>${serie.codigoSerie}</th>
						<th>${serie.nomeSerie}</th>
						<th>${serie.descricaoSerie}</th>
						<th>
							<form action="${pageContext.request.contextPath}/SerieController" method="post">
								<input type="text" name="serie-codigo-editar" value="${serie.codigoSerie}" hidden />
								<button id="serie-editar" type="submit" name="command" value="editar" class="btn btn-primary">
									<span class="glyphicon glyphicon-pencil"></span> Editar
								</button>
							</form>
						</th>
						<th>
							<form action="${pageContext.request.contextPath}/SerieController" method="post">
								<input type="text" name="serie-codigo-excluir" value="${serie.codigoSerie}" hidden />
								<button id="serie-excluir" type="submit" name="command" value="excluir" class="btn btn-danger">
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