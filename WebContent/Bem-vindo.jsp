<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/bf175f9272.js"></script>
<style><%@include file="./style.css"%></style>
<title>Gerenciador de Projetos</title>
</head>
<body>

    <header class="header">
        <div class="logo">
            <a href="">Gerenciador de Projetos</a>
            <i class="fa-solid fa-list-check"></i>
        </div>
        	<ul>
                <li class="login-logout">
                    <a href="entrada?action=Logar">Entrar</a>
                </li>
                <li class="login-logout">
                    <a href="entrada?action=CadastrarNovoUsuario">Increver-se</a>
                </li>
        	</ul>
        <div class="clear"></div>

    </header>

    <main class="container">
        <h1>Seja Bem-Vindo!</h1>
        <h2>Auxiliamos na administração de suas tarefas</h2>
        <h3>Faça login para ter acesso as nossas funcionalidades</h3>
        <div class="animated-cards">
        	<div class="card-1">
        		TO DO
        	</div>
        	<div  class="card-2">
        		DOING
        	</div>
        	<div  class="card-3">
        		DONE
        	</div>
        </div>
    </main>

</body>
</html>
