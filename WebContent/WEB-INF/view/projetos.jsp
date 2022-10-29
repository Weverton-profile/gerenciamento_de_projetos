<%@page import="br.com.gerenciamento.model.Projeto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="https://kit.fontawesome.com/bf175f9272.js"></script>
<style><%@include file="./css/projetos/style.css"%></style>
<title>Gerenciador de Projetos</title>
</head>
<body>
    <header class="header">
        <div class="logo">
            <h2>Gerenciador de Projetos</h2>
            <i class="fa-solid fa-list-check"></i>
        </div>
        	<ul>
                <li class="login-logout">
                   <a href="entrada?action=Logout">Sair</a>
                </li>
        	</ul>
        <div class="clear"></div>

    </header>
	<h1 class="titulo">Seus Projetos</h1>
	<div class="add-projeto">
		<a href="/gerenciamento_de_projetos/entrada?action=NovoProjeto" class="btn-add-projeto">Adicionare novos Projetos</a>
	</div>
    <main class="container-1">
		<ul>
			<c:forEach items="${projetos}" var="projeto">
				<c:forEach items="${membrosDoProjeto}" var="membroProjeto">
					<c:if test="${projeto.getId() == membroProjeto.getId_projeto()}">
						<c:if test="${projeto.getGerente_id() == idUsuario || membrosProjeto.getId_membro() == idUsuario }">
							<li class="cards">
								<h4>${projeto.getNome()}</h4>
								<p>${projeto.getDescricao()}</p>
								<p>Status: ${projeto.getAndamento()}</p>
								<a href="/gerenciamento_de_projetos/entrada?action=VerTarefa&id=${projeto.getId()}">Ver mais...</a>
								<c:if test="${projeto.getGerente_id() == idUsuario}">
									<a href="/gerenciamento_de_projetos/entrada?action=ExcluirProjeto&id=${projeto.getId()}">Excluir</a>
								</c:if>
							</li>
						</c:if>
					</c:if>
				</c:forEach>
			</c:forEach>
		</ul>
    </main>
    
</body>
</html>