<jsp:include page="../header.jsp" />

<h2 class="page_title text-center">
	<span>Cadastro de Séries</span><br />
	<span class="sep"></span><br/>
	<small>Formulário de cadastro de novas séries</small>
</h2>
<div class="row">
	<div class="col-md-12">
		<h3>Novo Cadastro</h3>
		<hr>
		<h4 id="feedback" class="text-center" style="color: red;">${feedback}</h4>
		<form role="form" id="serie-form" name="serie-form" method="post" action="${pageContext.request.contextPath}/SerieController" class="default-form">
			<div class="form-group col-md-12">
				<label class="sr-only" for="codigo-serie">Código da série: *</label>
				<input required type="text" class="form-control" id="codigo-serie" name="codigo-serie" placeholder="Código da série: *" maxlength="7" value="${serie.codigoSerie}" ${readonly} />
			</div>

			<div class="form-group col-md-12">
				<label class="sr-only" for="nome-serie">Nome da série: *</label>
				<input required type="text" class="form-control" id="nome-serie" name="nome-serie" placeholder="Nome da série: *" value="${serie.nomeSerie}" />
			</div>

			<div class="form-group col-md-12">
				<label class="sr-only" for="codigo-serie">Código da série: *</label>
				<textarea rows="4" cols="50" class="form-control" id="descricao-serie" name="descricao-serie" placeholder="Descrição da série: *">${serie.descricaoSerie}</textarea>
			</div>
						
			<div class="clearfix"></div>

			<input id="serie-salvar" name="command" type="submit" class="btn btn-lg btn-primary" value="Salvar" />
		</form>
	</div>
</div>

<jsp:include page="../footer.jsp" />