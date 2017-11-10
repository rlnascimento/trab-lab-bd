<!DOCTYPE html>
<html>
<head>
	<title>Séries</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta charset="utf-8" />
	
	<!-- Bootstrap -->
	<link href="${pageContext.request.contextPath}/jsp/css/bootstrap.min.css" rel="stylesheet" />
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,700" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/jsp/css/style.css" rel="stylesheet" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/shortcodes/shortcodes.css" type="text/css" />
</head>
<body>
	<header class="main__header">
		<div class="container">
			<nav class="navbar navbar-default" role="navigation"> 
				<div class="navbar-header">
					<h1 class="navbar-brand">
						<a href="${pageContext.request.contextPath}">Séries</a>
					</h1>
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#main-menu">
						<span class="sr-only">Menu</span>
						<span class="icon-bar"></span> <span class="icon-bar"></span>
						<span class="icon-bar"></span> 
					</button>
				</div>
				<div class="navbar-collapse navbar-right" id="main-menu">
					<ul class="nav navbar-nav">
						<li><a href="${pageContext.request.contextPath}">Home</a></li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">Série</a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/jsp/serie/form.jsp">Cadastro</a></li>
								<li><a href="${pageContext.request.contextPath}/jsp/serie/list.jsp">Lista</a></li>
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">Episódio</a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/jsp/episodio/form.jsp">Cadastro</a></li>
								<li><a href="${pageContext.request.contextPath}/jsp/episodio/list.jsp">Lista</a></li>
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">Relatório</a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/jsp/relatorio/series-report.jsp">Série</a></li>
								<li><a href="${pageContext.request.contextPath}/jsp/relatorio/episodes-report.jsp">Episódio</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<!-- /.navbar-collapse --> 
			</nav>
		</div>
	</header>
	<section class="default-container">
		<section class="default-section">
			<div class="container">
	