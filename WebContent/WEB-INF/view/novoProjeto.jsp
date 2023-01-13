<%@page import="br.com.gerenciamento.model.Projeto"%>
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
<script src="https://kit.fontawesome.com/bf175f9272.js"></script>
<style><%@ include file="./css/novoProjeto/style.css"%></style>
<title>Gerenciador de Projetos</title>
</head>
<body>
	<header class="header">
		<div class="logo">
			<a href="entrada?action=Projetos">Gerenciador de Projetos</a>
			<i class="fa-solid fa-list-check"></i>
		</div>
		<ul>
			<li class="login-logout"><a href="entrada?action=Logout">Sair</a></li>
		</ul>
		<div class="clear"></div>
	</header>
	<h1 class="titulo">Novo Projeto</h1>
	<div class="login">
		<form class="form" action="${linkEntradaServlet}" method="post">
			<label for="nomeProjeto">Nome do Projeto:</label><br>
			<input type="text" name="nomeProjeto" id="" required="required"><br>
			<label for="descricao">Descrição:</label><br>
			<textarea id="w3review" name="descricao" rows="4" cols="50" maxlength="250"></textarea>
			<input type="hidden" name="action" value="CriarProjeto"><br>
			<input type="submit">
		</form>
	</div>
</body>
</html>