<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:url value="/entrada" var="linkEntradaServlet"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/bf175f9272.js"></script>
<style><%@include file="./css/cadastrar_usuario/style.css"%></style>
<title>Gerenciador de Projetos</title>
</head>
<body>
    
    <div class="container-css">
        <div class="logo">
            <p>Seja Bem-Vindo!</p>
            <h2>Faça sua inscrição</h2>
        </div>
	
        <div class="login">
            <form class="form" action="${linkEntradaServlet}" method="post">
                    <label for="nome">Nome de Usuario:</label><br>
                    <input type="text" name="nome" id="" required="required"><br>
                    <label for="email">E-mail:</label><br>
                    <input type="email" name="email" id="" required="required"><br>
                    <label for="">Senha:</label><br>
                    <input type="password" name="senha" id="" required="required">
                    <input type="hidden" name="action" value="Inscricao">
                    <a href="entrada?action=Logar">Já possuo uma conta</a>
                    <input type="submit">
            </form>
        </div>
    </div>

</body>
</html>
