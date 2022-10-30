<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:url value="/entrada" var="linkEntradaServlet"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="https://kit.fontawesome.com/bf175f9272.js"></script>
<style><%@include file="./css/ver_tarefa/style.css"%></style>
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
	<h1 class="titulo">${projeto.getNome()}</h1>
	<div class="container-1">
		<div class="todo">
			<c:if test="${projeto.getGerente_id() == idUsuario}">
				<form class="form" action="${linkEntradaServlet}" method="post">
					<label for="nomeTarefa">Nova Tarefa:</label>
					<input type="text" name="nomeTarefa" id="" required="required">
					<input type="hidden" name="action" value="NovaTarefa">
					<input type="hidden" name="idProjeto" value="${projeto.getId() }">
					<input type="submit">
				</form>
			</c:if>
			<c:forEach items="${tarefas}" var="tarefa">
				<c:if test="${tarefa.getAndamento().equals('PARA FAZER')}">
					<div class="card">
						<div>
							${tarefa.getNome()}
							<div class="links">
								<a href="entrada?action=AtualizarTarefa&status=Fazendo">FAZENDO</a>
								<c:if test="${projeto.getGerente_id() == idUsuario}">
									<a href="entrada?action=ExcluirTarefa&idTarefa=${tarefa.getId() }"><i class="fa-solid fa-trash"></i></a>
								</c:if>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
		<div class="doing">
			<c:forEach items="${tarefas}" var="tarefa">
				<c:if test="${tarefa.getAndamento().equals('FAZENDO')}">
					<div class="card">
						<div>
							${tarefa.getNome()}
							<div class="links">
								<c:if test="${tarefa.getMembro_id() == idUsuario}">
									<a href="entrada?action=AtualizarTarefa&status=Feito">FEITO</a>
								</c:if>
								<c:if test="${projeto.getGerente_id() == idUsuario}">
									<a href="entrada?action=ExcluirTarefa&idTarefa=${tarefa.getId() }"><i class="fa-solid fa-trash"></i></a>
								</c:if>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
		<div class="done">
			<c:forEach items="${tarefas}" var="tarefa">
				<c:if test="${tarefa.getAndamento().equals('FEITO')}">
					<div class="card">
						<div>
							${tarefa.getNome()}
							<div class="links">
								<i class="fa-solid fa-circle-check"></i>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
    
</body>
</html>