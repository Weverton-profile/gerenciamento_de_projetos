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
            <a href="entrada?action=Projetos">Gerenciador de Projetos</a>
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
				<label for="stringUsuario">Adicionar Membro:</label>
				<input type="text" name="stringUsuario" id="" required="required" placeholder="Buscar Usuario">
				<input type="hidden" name="action" value="BuscarUsuario">
				<input type="hidden" name="idProjeto" value="${projeto.getId() }">
				<input type="submit">
			</form>
		</div>
	</c:if>
	<div class="opcoes-projeto">
		<c:if test="${projeto.getGerente_id() == idUsuario}">
			<c:choose>
				<c:when test="${andamentos.contains('PARA FAZER') || andamentos.contains('FAZENDO')}">
					<a href="" style="pointer-events: none;" class="opcoes-projeto-btn">${projeto.getAndamento()}</a>
				</c:when>
				<c:otherwise>
					<c:if test="${projeto.getAndamento().equals('EM ANDAMENTO')}">
						<a 
						href="entrada?action=AtualizarProjeto&idProjeto=${projeto.getId() }
						&andamento=${projeto.getAndamento()}
						&id=${projeto.getGerente_id()}" 
						class="opcoes-projeto-btn">FINALIZAR PROJETO</a>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:if>
		<a href="entrada?action=GerarRelatorio&idProjeto=${projeto.getId() }" target="_blank" class="opcoes-projeto-btn">Relatorio <i class="fa-solid fa-file-pdf"></i></a>
	</div>
	<h1 class="titulo">${projeto.getNome()}</h1>
	<div class="container-1">
		<div class="todo">
			<c:if test="${projeto.getGerente_id() == idUsuario}">
					<form class="form form-tarefa" action="${linkEntradaServlet}" method="post">
						<label for="nomeTarefa">Nova Tarefa:</label>
						<input type="text" name="nomeTarefa" id="" required="required"><br>
						<label for="nomeTarefa">Tempo da Tarefa:</label>
						<input type="hidden" name="action" value="NovaTarefa">
						<input type="number" name="tempoTarefa" id="" required="required">
						<input type="hidden" name="idProjeto" value="${projeto.getId() }">
						<c:choose>
							<c:when test="${projeto.getAndamento().equals('FINALIZADO')}">
								<input type="submit" disabled="disabled">
							</c:when>
							<c:otherwise>
								<input type="submit">
							</c:otherwise>
						</c:choose>
					</form>
			</c:if>
			<c:forEach items="${tarefas}" var="tarefa">
				<c:if test="${tarefa.getAndamento().equals('PARA FAZER')}">
					<div class="card">
						<div>
							${tarefa.getNome()} | ${tarefa.getTempo()}H estimado
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
							${tarefa.getNome()} | ${tarefa.getTempo()}H estimado
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
							<c:if test="${tarefa.tempoReal() > tarefa.getTempo()}">
								<span>${tarefa.getNome()} | ${tarefa.getTempo()}H estimado | <span style="color: red;">${tarefa.tempoReal()}h tempo real</span></span>
							</c:if>
							<c:if test="${tarefa.tempoReal() < tarefa.getTempo()}">
								<span>${tarefa.getNome()} | ${tarefa.getTempo()}H estimado | <span style="color: #00FF7F;">${tarefa.tempoReal()}h tempo real</span></span>
							</c:if>
							<div class="links">
								<i class="feito fa-solid fa-circle-check"></i>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	<h4 class="titulo">Membros do Projeto:</h4>
	<div class="membros">
		<c:forEach items="${usuarios}" var="usuario">
			<c:if test="${usuario.getId() != idUsuario}">
				<div class="card-membro">
					<c:if test="${usuario.getId() == projeto.getGerente_id()}">
						<span>Administrador : ${usuario.getNomeUsuario()}</span> 
					</c:if>
					<c:if test="${usuario.getId() != projeto.getGerente_id()}">
						<span>${usuario.getNomeUsuario()}</span>
					</c:if>
					 - <span>${usuario.getEmail()}</span> 
					<c:if test="${projeto.getGerente_id() == idUsuario}">
						- <a href="entrada?action=ExcluirDoProjeto&idProjeto=${idProjeto}&usuarioId=${usuario.getId()}"><i class="excluir fa-solid fa-trash"></i></a>
					</c:if>
				</div>
			</c:if>
		</c:forEach>
	</div>
</body>
</html>