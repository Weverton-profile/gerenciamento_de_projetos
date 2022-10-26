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
                    <a href="/gerenciamento_de_projetos/entrada?action=Logar"><i class="fa-solid fa-user"></i> Perfil</a>
                </li>
                <li class="login-logout">
                   <a href="entrada?action=Logout">Sair</a>
                </li>
        	</ul>
        <div class="clear"></div>

    </header>
	<h1 class="titulo">Seus Projetos</h1>
    <main class="container-1">
		<ul>
			<c:forEach items="${projetos}" var="projeto">
				
				<li class="cards">
					<h4>${projeto.getNome()}</h4>
					<p>${projeto.getDescricao()}</p>
					<p>Status: ${projeto.getAndamento()}</p>
					<a href="/gerenciamento_de_projetos/entrada?action=VerTarefa&id=${projeto.getId()}">Ver mais...</a>
					<a>Excluir</a>
				</li>
			</c:forEach>
		</ul>
    </main>
    
</body>
</html>