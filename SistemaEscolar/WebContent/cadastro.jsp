<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

```
<div class="container">
    <div class="login-card">

        <div class="logo">
            <h1>Criar conta</h1>
            <p>Preencha seus dados</p>
        </div>

        <form>
            <div class="input-group">
                <label for="nome">Nome</label>
                <input 
                    type="text" 
                    id="nome" 
                    placeholder="Digite seu nome"
                    required
                >
            </div>

            <div class="input-group">
                <label for="email">E-mail</label>
                <input 
                    type="email" 
                    id="email" 
                    placeholder="Digite seu e-mail"
                    required
                >
            </div>

            <div class="input-group">
                <label for="senha">Senha</label>
                <input 
                    type="password" 
                    id="senha" 
                    placeholder="Digite sua senha"
                    required
                >
            </div>

            <button type="submit">Cadastrar</button>
        </form>

        <div class="footer">
            <p>Já possui uma conta?</p>
            <a href="index.jsp">Voltar para o login</a>
        </div>

    </div>
</div>
```

</body>
</html>
