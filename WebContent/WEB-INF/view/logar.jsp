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
<style><%@include file="./css/logar/style.css"%></style>
<title>Gerenciador de Projetos</title>
</head>
<body>
    <div class="container-1">
        <div class="logo">
           	<p>Seja Bem-Vindo!</p>
        </div>
        <div class="login">
            <form class="form" action="${linkEntradaServlet}" method="post">
                    <label for="email">E-mail:</label><br>
                    <input type="email" name="email" id="" required="required"><br>
                    <label for="">Senha:</label><br>
                    <input type="password" name="senha" id="" required="required">
                    <input type="hidden" name="action" value="Login">
                    <a href="/gerenciamento_de_projetos/entrada?action=CadastrarNovoUsuario">não tenho uma conta ainda</a>
                    <input type="submit">
            </form>
        </div>
    </div>

</body>
</html>