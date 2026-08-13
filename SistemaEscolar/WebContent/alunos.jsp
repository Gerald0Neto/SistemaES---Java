<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Classes.Aluno" %>
<%
    if (session.getAttribute("idusuario") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
     <link rel="stylesheet" href="css/alunos.css">

    <title>Gestão Escolar - Alunos</title>


</head>


<body>

		<h5 class="mt-3 text-danger text-center" style="color:red;">
            <%= request.getAttribute("msg") != null ? request.getAttribute("msg") : "" %>
        </h5>
<!-- =========================
     MENU
========================= -->

<header class="topbar">
    <nav class="menu">
        <a
            href="AlunosController"
            class="active"
        >
            Alunos
        </a>
        <a href="CursoController">
            Cursos
        </a>
        <a href="matriculas.jsp">
            Matrículas
        </a>
    </nav>
</header>



<!-- =========================
     CONTEÚDO
========================= -->

<main class="container">

    <div class="page-header">
        <div class="page-title">
            <h1>
                Alunos
            </h1>
            <p>
                Gerencie os alunos cadastrados no sistema.
            </p>
        </div>

        <!--
            LABEL ABRE O MODAL
            SEM JAVASCRIPT
        -->
        <label
            for="modalNovoAluno"
            class="btn btn-primary"
        >
            + Novo aluno
        </label>

    </div>

    <!-- =========================
         PESQUISA
    ========================= -->

    <div class="card filters">

        <form
            action="AlunosController"
            method="get"
        >
        <input type="hidden" name="item" value="pesquisar">
            <div class="filters-grid">


                <div class="form-group">

                    <label>
                        Nome do aluno
                    </label>

                    <input
                        type="text"
                        name="nome"
                        placeholder="Digite o nome..."
                    >

                </div>


                <div class="form-group">

                    <label>
                        CPF
                    </label>

                    <input
                        type="text"
                        name="cpf"
                        placeholder="Digite o CPF..."
                    >

                </div>


                <button
                    type="submit"
                    class="btn btn-filter"
                >
                    Pesquisar
                </button>
               

            </div>

        </form>

    </div>



    <!-- =========================
         LISTA
    ========================= -->

    <div class="card table-card">


        <div class="table-header">

            <h2>
                Lista de alunos
            </h2>


            <span class="total">
                1 aluno
            </span>

        </div>


        <div class="table-wrapper">


            <table>

                <thead>

                    <tr>

                        <th>
                            Aluno
                        </th>

                        <th>
                            CPF
                        </th>

                        <th>
                            Data de nascimento
                        </th>

                        <th>
                            Telefone
                        </th>

                        <th>
                            Status
                        </th>

                        <th>
                            Ações
                        </th>

                    </tr>

                </thead>


                <tbody>
					
					<%
					    List<Aluno> alunos = (List<Aluno>) request.getAttribute("alunos");
					
					    if (alunos != null && !alunos.isEmpty()) {
					
					        for (Aluno aluno : alunos) {
					
					            String nome = aluno.getNome();
					            String inicial = nome != null && !nome.isEmpty()
					                    ? nome.substring(0, 1).toUpperCase()
					                    : "?";
					%>
					
					<tr>
					
					    <!-- ALUNO -->
					    <td>
					
					        <div class="student">
					
					            <div class="avatar">
					                <%= inicial %>
					            </div>
					
					            <div>
					
					                <div class="student-name">
					                    <%= aluno.getNome() %>
					                </div>
					
					                <div class="student-id">
					                    ID #<%= aluno.getId() %>
					                </div>
					
					            </div>
					
					        </div>
					
					    </td>
					
					
					    <!-- CPF -->
					    <td>
					        <%= aluno.getCpf() %>
					    </td>
					
					
					    <!-- DATA DE NASCIMENTO -->
					    <td>
					        <%= aluno.getDataNascimento() %>
					    </td>
					
					
					    <!-- TELEFONE -->
					    <td>
					        <%= aluno.getTelefone() %>
					    </td>
				       <%
						    int statusAluno = aluno.getStatus();
						
						    int novoStatus = (statusAluno == 1) ? 2 : 1;
						%>
					
					    <!-- STATUS -->
					    <td>
					
					        <span class="status active">
					
					            <span class="status-dot"></span>
					
					            <% if (statusAluno == 1) { %>
							          ATIVO
							    <% } else { %>
							          DESATIVADO
							    <% } %>
					
					        </span>
					
					    </td>
					
					
					    <!-- AÇÕES -->
					    <td>
					
					        <div class="actions">
					
					            <label
								    for="modalEditarAluno<%= aluno.getId() %>"
								    class="action-btn"
								    title="Editar"
								>
								    ✏️
								</label>
										 
							
							<a
							    href="AlunosController?item=statusAluno&id=<%= aluno.getId() %>&status=<%= novoStatus %>"
							    class="action-btn"
							    title="Ativar/Desativar"
							    onclick="return confirm('Deseja realmente mudar o status do aluno?');"
							>
							    <% if (statusAluno == 1) { %>
							                  ✕
							    <% } else { %>
							                  ✓
							    <% } %>
							</a>
					
					
					            <a
					                href="AlunosController?item=delete&id=<%= aluno.getId() %>"
					                class="action-btn"
					                title="Excluir"
					                onclick="return confirm('Deseja realmente excluir este aluno?');"
					            >
					                🗑️
					            </a>
					
					        </div>
					
					    </td>
					
					</tr>
					
					<%
					        }
					
					    } else {
					%>
					
					<tr>
					
					    <td colspan="6" style="text-align: center; padding: 40px;">
					
					        <div style="font-size: 35px; margin-bottom: 10px;">
					            👨‍🎓
					        </div>
					
					        <div style="font-weight: 600; color: #374151;">
					            Nenhum aluno encontrado
					        </div>
					
					        <div style="font-size: 13px; color: #9ca3af; margin-top: 5px;">
					            Cadastre um novo aluno para começar.
					        </div>
					
					    </td>
					
					</tr>
					
					<%
					    }
					%>


                </tbody>

            </table>

        </div>

    </div>

</main>

<!-- =====================================================
     MODAIS DE EDIÇÃO DOS ALUNOS
     SEM JAVASCRIPT
===================================================== -->

<%
    if (alunos != null && !alunos.isEmpty()) {

        for (Aluno aluno : alunos) {
%>

<input
    type="checkbox"
    id="modalEditarAluno<%= aluno.getId() %>"
    class="modal-checkbox"
>

<div class="modal">

    <div class="modal-box">

        <!-- HEADER -->

        <div class="modal-header">

            <h2>
                Editar aluno
            </h2>

            <label
                for="modalEditarAluno<%= aluno.getId() %>"
                class="modal-close"
            >
                ×
            </label>

        </div>


        <!-- FORMULÁRIO -->

        <form
            action="AlunosController"
            method="post"
        >

            <input
                type="hidden"
                name="item"
                value="editarAluno"
            >

            <input
                type="hidden"
                name="id"
                value="<%= aluno.getId() %>"
            >


            <div class="modal-body">

                <div class="form-grid">


                    <!-- NOME -->

                    <div class="form-group form-full">

                        <label>
                            Nome completo
                        </label>

                        <input
                            type="text"
                            name="nome"
                            value="<%= aluno.getNome() %>"
                            placeholder="Digite o nome completo"
                            required
                        >

                    </div>


                    <!-- CPF -->

                    <div class="form-group">

                        <label>
                            CPF
                        </label>

                        <input
                            type="text"
                            name="cpf"
                            value="<%= aluno.getCpf() %>"
                            placeholder="000.000.000-00"
                            required
                        >

                    </div>


                    <!-- DATA DE NASCIMENTO -->

                    <div class="form-group">

                        <label>
                            Data de nascimento
                        </label>

                        <input
                            type="date"
                            name="dataNascimento"
                            value="<%= aluno.getDataNascimento() %>"
                            required
                        >

                    </div>


                    <!-- TELEFONE -->

                    <div class="form-group">

                        <label>
                            Telefone
                        </label>

                        <input
                            type="text"
                            name="telefone"
                            value="<%= aluno.getTelefone() %>"
                            placeholder="(99) 99999-9999"
                        >

                    </div>


                    <!-- EMAIL -->

                    <div class="form-group">

                        <label>
                            E-mail
                        </label>

                        <input
                            type="email"
                            name="email"
                            value="<%= aluno.getEmail() %>"
                            placeholder="aluno@email.com"
                        >

                    </div>


                </div>

            </div>


            <!-- FOOTER -->

            <div class="modal-footer">

                <label
                    for="modalEditarAluno<%= aluno.getId() %>"
                    class="btn btn-cancel"
                >
                    Cancelar
                </label>


                <button
                    type="submit"
                    class="btn btn-primary"
                >
                    Salvar alterações
                </button>

            </div>

        </form>

    </div>

</div>

<%
        }
    }
%>


<!-- =====================================================
     MODAL NOVO ALUNO
     SEM JAVASCRIPT
===================================================== -->


<input
    type="checkbox"
    id="modalNovoAluno"
    class="modal-checkbox"
>


<div class="modal">


    <div class="modal-box">


        <!-- HEADER -->

        <div class="modal-header">

            <h2>
                Cadastrar novo aluno
            </h2>


            <label
                for="modalNovoAluno"
                class="modal-close"
            >
                ×
            </label>

        </div>



        <!-- FORMULÁRIO -->

        <form
            action="AlunosController"
            method="post"          
        >
        <input type="hidden" name="alunosCadastra" value="cadastraAluno">

            <div class="modal-body">


                <div class="form-grid">


                    <!-- NOME -->

                    <div class="form-group form-full">

                        <label>
                            Nome completo
                        </label>

                        <input
                            type="text"
                            name="nome"
                            placeholder="Digite o nome completo"
                            required
                        >

                    </div>



                    <!-- CPF -->

                    <div class="form-group">

                        <label>
                            CPF
                        </label>

                        <input
                            type="text"
                            name="cpf"
                            placeholder="000.000.000-00"
                            required
                        >

                    </div>



                    <!-- NASCIMENTO -->

                    <div class="form-group">

                        <label>
                            Data de nascimento
                        </label>

                        <input
                            type="date"
                            name="dataNascimento"
                            required
                        >

                    </div>



                    <!-- TELEFONE -->

                    <div class="form-group">

                        <label>
                            Telefone
                        </label>

                        <input
                            type="text"
                            name="telefone"
                            placeholder="(99) 99999-9999"
                        >

                    </div>



                    <!-- EMAIL -->

                    <div class="form-group">

                        <label>
                            E-mail
                        </label>

                        <input
                            type="email"
                            name="email"
                            placeholder="aluno@email.com"
                        >

                    </div>



                 


                </div>

            </div>



            <!-- FOOTER -->

            <div class="modal-footer">


                <label
                    for="modalNovoAluno"
                    class="btn btn-cancel"
                >
                    Cancelar
                </label>


                <button
                    type="submit"
                    class="btn btn-primary"
                >
                    Cadastrar aluno
                </button>


            </div>


        </form>

    </div>

</div>


</body>

</html>
