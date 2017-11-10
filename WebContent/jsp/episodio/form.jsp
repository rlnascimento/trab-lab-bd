<jsp:include page="../header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="serie" class="labbd.series.controller.SerieController" />

<h2 class="page_title text-center">
	<span>Cadastro de Epis�dios</span><br />
	<span class="sep"></span><br/>
	<small>Formul�rio de cadastro de novos epis�dios</small>
</h2>
<div class="row">
	<div class="col-md-12">
		<h3>Novo Cadastro</h3>
		<hr>
		<h4 id="feedback" class="text-center" style="color: red;">${feedback}</h4>
		<form role="form" id="episodio-form" name="episodio-form" method="post" action="${pageContext.request.contextPath}/EpisodioController" class="default-form">
			<div class="form-group col-md-12">
				<label class="sr-only" for="numero-episodio">N�mero do epis�dio: *</label>
				<input required type="text" class="form-control" id="numero-episodio" name="numero-episodio" placeholder="N�mero do epis�dio: *" value="${episodio.numero}" ${readonly} />
			</div>
			
			<div class="form-group col-md-12">
				<label class="sr-only" for="temporada-episodio">Temporada: *</label>
				<input required type="text" class="form-control" id="temporada-episodio" name="temporada-episodio" placeholder="Temporada: *" value="${episodio.temporada}" ${readonly} />
			</div>
			
			<div class="form-group col-md-12">
				<label class="" for="codigo-serie-temporada">C�digo da S�rie: *<br/></label>
				<select required class="form-control" id="codigo-serie-temporada" name="codigo-serie-temporada">
					<c:forEach var="serie" items="${serie.lista}">
						<option value="${serie.codigoSerie}" ${(episodio == null || serie.codigoSerie == episodio.codigoSerie) ? 'selected' : 'disabled'}>${serie.codigoSerie} - ${serie.nomeSerie}</option>
					</c:forEach>
				</select>
			</div>
					
			<div class="form-group col-md-12">
				<label class="sr-only" for="exibicao-episodio">Data de exibi��o: *</label>
				<input required type="date" class="form-control data" id="exibicao-episodio" name="exibicao-episodio" placeholder="Data de exibi��o em ano, m�s e dia: *" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${episodio.dataExibicao.time}"/>" />
			</div>
			
			<div class="form-group col-md-12">
				<label  for="duracao-episodio">Dura��o: *</label>
				<input required type="text" class="form-control duracao" id="duracao-episodio" name="duracao-episodio" placeholder="Dura��o em horas, minutos e segundos: *" value="${episodio.duracao}" />
			</div>
			
			<div class="form-group col-md-12">
				<label class="sr-only" for="descricao-episodio">Descri��o: *</label>
				<input required type="text" class="form-control" id="descricao-episodio" name="descricao-episodio" placeholder="Descri��o do epis�dio: *" value="${episodio.descricao}" />
			</div>
						
			<div class="clearfix"></div>

			<input id="episodio-salvar" name="command" type="submit" class="btn btn-lg btn-primary" value="Salvar" />
		</form>
	</div>
</div>

<jsp:include page="../footer.jsp" />