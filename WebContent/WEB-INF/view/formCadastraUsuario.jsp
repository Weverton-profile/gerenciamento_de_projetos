<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
            <h2>Fa�a sua inscri��o</h2>
        </div>

        <div class="login">
            <form class="form" action="">
                    <label for="nome">Nome de Usuario:</label><br>
                    <input type="text" name="nome" id=""><br>
                    <label for="email">E-mail:</label><br>
                    <input type="email" name="email" id=""><br>
                    <label for="">Senha:</label><br>
                    <input type="password" name="" id="">
                    <a href="/gerenciamento_de_projetos/entrada?action=Logar">j� possuo uma conta</a>
                    <input class="enviar" type="submit" value="Inscrever-se">
            </form>
        </div>
    </div>

</body>
</html>