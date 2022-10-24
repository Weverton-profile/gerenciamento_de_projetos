<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/bf175f9272.js"></script>
<style><%@include file="./css/logar/style.css"%></style>
<title>Insert title here</title>
</head>
<body>
    <div class="container-1">
        <div class="logo">
           	<p>Seja Bem-Vindo!</p>
        </div>

        <div class="login">
            <form class="form" action="">
                    <label for="email">E-mail:</label><br>
                    <input type="email" name="email" id=""><br>
                    <label for="">Senha:</label><br>
                    <input type="password" name="" id="">
                    <a href="/gerenciamento_de_projetos/entrada?action=CadastrarNovoUsuario">não tenho uma conta ainda</a>
                    <input class="enviar" type="submit" value="Login">
            </form>
        </div>
    </div>

</body>
</html>