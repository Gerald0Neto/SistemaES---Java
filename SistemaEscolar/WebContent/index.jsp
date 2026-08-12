<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Sistema</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="container">
    <div class="login-card">

        <div class="logo">
            <h1>SistemaEscolar</h1>
            <p>Entre na sua conta</p>
        </div>

        <form action="${pageContext.request.contextPath}/Login" method="POST">
            <div class="input-group">
                <label for="email">E-mail</label>
                <input 
                	name="email"
                    type="email" 
                    id="email" 
                    placeholder="Digite seu e-mail"
                    required
                >
            </div>

            <div class="input-group">
                <label for="senha">Senha</label>
                <input 
                	name="senha"
                    type="password" 
                    id="senha" 
                    placeholder="Digite sua senha"
                    required
                >
            </div>

            <button type="submit">Entrar</button>
        </form>

        <div class="footer">
            <p>Não possui uma conta?</p>
            <a href="cadastro.jsp">Criar uma conta</a>
        </div>
        
        <h5 class="mt-3 text-danger text-center">
            <%= request.getAttribute("msg") != null ? request.getAttribute("msg") : "" %>
        </h5>

    </div>
</div>

</body>
</html>
