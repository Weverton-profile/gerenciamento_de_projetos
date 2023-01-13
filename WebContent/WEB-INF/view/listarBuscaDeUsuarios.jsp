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
<style><%@include file="./css/listar_busca_de_usuarios/style.css"%></style>
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
    <h1 class="titulo">Sua consulta retornou essas pessoas</h1>
    <div class="membros">
		<c:forEach items="${usuariosRetornados}" var="usuarios">
			<c:if test="${usuarios.getId() != idUsuario}">
				<div class="card-membro">
					<span>${usuarios.getNomeUsuario()}</span> - <span>${usuarios.getEmail()}</span> - <a href="entrada?action=InserirNoProjeto&idProjeto=${idProjeto}&usuarioId=${usuarios.getId()}"><i class="add fa-solid fa-user-plus"></i></a>
				</div>
			</c:if>
		</c:forEach>
	</div>
</body>
</html>