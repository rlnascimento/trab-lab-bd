<jsp:useBean id="relatorio" class="labbd.series.controller.RelatorioEpisodioController" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="../header.jsp" />

<!-- jQuery Library (skip this step if already called on page ) -->
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>

<h2 class="page_title text-center">
	<span>Relatório de Episódios</span><br />
	<span class="sep"></span><br/>
</h2>
<div class="row">
	<div class="col-md-12">
		<h4 id="feedback" class="text-center" style="color: red;">${feedback}</h4>
		<table class="dc_tables2_0" style="width: 100%; margin-top: 10px;">
			<tbody>
				<thead>
					<tr>
						<th class="bold">Número</th>
						<th class="bold">Temporada</th>
						<th class="bold">Código da Série</th>
						<th class="bold">Data de Exibição</th>
						<th class="bold">Duração</th>
						<th class="bold">Descrição da Série</th>
						<th class="bold">Operação</th>
					</tr>
				</thead>
				<c:forEach var="relatorio" items="${relatorio.relatorio}">
				  <tr>
				    <th>${relatorio.numero}</th>
				    <th>${relatorio.temporada}</th>
				    <th>${relatorio.codigoSerie}</th>
						<th><fmt:formatDate pattern="dd/MM/yyyy" value="${relatorio.dataExibicao.time}"/></th>
						<th>${relatorio.duracao}</th>
						<th>${relatorio.descricao}</th>
						<th>${relatorio.operacao} em <fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${relatorio.dataOperacao.time}"/></th>
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