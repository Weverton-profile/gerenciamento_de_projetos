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
	<c:if test="${projeto.getGerente_id() == idUsuario}">
		<div class="opcoes-gerente">
			<form class="form" action="${linkEntradaServlet}" method="post">
				<label for="nomeTarefa">Adicionar Membro ao Projeto:</label>
				<input type="text" name="nomeTarefa" id="" required="required" placeholder="Buscar por E-mail">
				<input type="hidden" name="action" value="NovaTarefa">
				<input type="hidden" name="idProjeto" value="${projeto.getId() }">
				<input type="submit">
			</form>
			<a href="">Marcar como concluido</a>
		</div>
	</c:if>
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
								<a class="avanco" href="entrada?action=AtualizarTarefa&status=Fazendo&idTarefa=${tarefa.getId() }&idProjeto=${projeto.getId()}">FAZENDO</a>
								<c:if test="${projeto.getGerente_id() == idUsuario}">
									<a href="entrada?action=ExcluirTarefa&andamento=${tarefa.getAndamento() }&idTarefa=${tarefa.getId() }&idProjeto=${projeto.getId()}"><i class="excluir fa-solid fa-trash"></i></a>
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
									<a class="avanco" href="entrada?action=AtualizarTarefa&status=Feito&idTarefa=${tarefa.getId() }&idProjeto=${projeto.getId()}">FEITO</a>
								</c:if>
								<c:if test="${projeto.getGerente_id() == idUsuario}">
									<a href="entrada?action=ExcluirTarefa&andamento=${tarefa.getAndamento() }&idTarefa=${tarefa.getId()}&idProjeto=${projeto.getId()}"><i class="excluir fa-solid fa-trash"></i></a>
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
								<i class="feito fa-solid fa-circle-check"></i>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	<c:if test="${projeto.getGerente_id() == idUsuario}">
		<div class="membros">
			<div class="card-membro">
				<span>Nome do Membro</span> - <span>Email Membro</span> - <i class="excluir fa-solid fa-trash"></i>
			</div>
			<div class="card-membro">
				<span>Nome do Membro</span> - <span>Email Membro</span> - <i class="excluir fa-solid fa-trash"></i>
			</div>
		</div>
    </c:if>
</body>
</html>