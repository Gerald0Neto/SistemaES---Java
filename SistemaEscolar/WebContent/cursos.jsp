<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="Classes.Curso" %>

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

    <title>Gestão Escolar - Cursos</title>

</head>

<body>

    <!-- =========================
         MENSAGEM
    ========================= -->

    <h5 class="mt-3 text-danger text-center" style="color:red;">
        <%= request.getAttribute("msg") != null
            ? request.getAttribute("msg")
            : "" %>
    </h5>


    <!-- =========================
         MENU
    ========================= -->

    <header class="topbar">

        <nav class="menu">

            <a href="AlunosController">
                Alunos
            </a>

            <a href="CursoController" class="active">
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


        <!-- CABEÇALHO -->

        <div class="page-header">

            <div class="page-title">

                <h1>
                    Cursos
                </h1>

                <p>
                    Gerencie os cursos cadastrados no sistema.
                </p>

            </div>


            <!-- ABRIR MODAL -->

            <label
                for="modalNovoCurso"
                class="btn btn-primary"
            >
                + Novo curso
            </label>

        </div>


        <!-- =========================
             PESQUISA
        ========================= -->

        <div class="card filters">

            <form
                action="CursoController"
                method="get"
            >

                <input
                    type="hidden"
                    name="item"
                    value="pesquisar"
                >


                <div class="filters-grid">


                    <!-- NOME -->

                    <div class="form-group">

                        <label>
                            Nome do curso
                        </label>

                        <input
                            type="text"
                            name="nome"
                            placeholder="Digite o nome do curso..."
                        >

                    </div>


                    <!-- STATUS -->

                    <div class="form-group">

                        <label>
                            Status
                        </label>

                        <select name="status">

                            <option value="">
                                Todos
                            </option>

                            <option value="1">
                                Ativos
                            </option>

                            <option value="0">
                                Desativados
                            </option>

                        </select>

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
                    Lista de cursos
                </h2>

                <span class="total">

                    <%
                        List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");

                        int totalCursos = cursos != null ? cursos.size() : 0;
                    %>

                    <%= totalCursos %> curso(s)

                </span>

            </div>


            <div class="table-wrapper">

                <table>

                    <thead>

                        <tr>

                            <th>
                                Curso
                            </th>

                            <th>
                                Descrição
                            </th>

                            <th>
                                Carga horária
                            </th>

                            <th>
                                Valor
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
                        if (cursos != null && !cursos.isEmpty()) {

                            for (Curso curso : cursos) {

                                String nomeCurso = curso.getNome();

                                String inicial =
                                    nomeCurso != null && !nomeCurso.isEmpty()
                                    ? nomeCurso.substring(0, 1).toUpperCase()
                                    : "?";

                                Boolean statusCurso = curso.getAtivo();

                                Boolean novoStatus = statusCurso != null && !statusCurso;
                    %>


                        <tr>


                            <!-- CURSO -->

                            <td>

                                <div class="student">

                                    <div class="avatar">
                                        <%= inicial %>
                                    </div>

                                    <div>

                                        <div class="student-name">
                                            <%= curso.getNome() %>
                                        </div>

                                        <div class="student-id">
                                            ID #<%= curso.getId() %>
                                        </div>

                                    </div>

                                </div>

                            </td>


                            <!-- DESCRIÇÃO -->

                            <td>

                                <%
                                    String descricao =
                                        curso.getDescricao();

                                    if (descricao != null
                                        && descricao.length() > 60) {

                                        descricao =
                                            descricao.substring(0, 60)
                                            + "...";
                                    }
                                %>

                                <%= descricao != null
                                    ? descricao
                                    : "Sem descrição" %>

                            </td>


                            <!-- CARGA HORÁRIA -->

                            <td>

                                <%= curso.getCarga_horaria() %> horas

                            </td>


                            <!-- VALOR -->

                            <td>R$<%= curso.getValor() %></td>


                            <!-- STATUS -->

                            <td>

                                <span class="status active">

                                    <span class="status-dot"></span>

                                    <% if (statusCurso) { %>

									    ATIVO
									
									<% } else { %>
									
									    DESATIVADO
									
									<% } %>

                                </span>

                            </td>


                            <!-- DATA -->

                    

                            <!-- AÇÕES -->

                            <td>

                                <div class="actions">


                                    <!-- EDITAR -->

                                   <label
									    for="modalEditarCurso<%= curso.getId() %>"
									    class="action-btn"
									    title="Editar"
									>
									    ✏️
									</label>


                                    <!-- STATUS -->

                                    <a
                                        href="CursoController?item=statusCurso&id=<%= curso.getId() %>&status=<%= novoStatus %>"
                                        class="action-btn"
                                        title="Ativar/Desativar"
                                        onclick="return confirm('Deseja realmente mudar o status do curso?');"
                                    >

                                        <% if (statusCurso) { %>

                                            ✕

                                        <% } else { %>

                                            ✓

                                        <% } %>

                                    </a>


                                    <!-- EXCLUIR -->

                                    <a
                                        href="CursoController?item=delete&id=<%= curso.getId() %>"
                                        class="action-btn"
                                        title="Excluir"
                                        onclick="return confirm('Deseja realmente excluir este curso?');"
                                    >
                                        🗑️
                                    </a>


                                </div>

                            </td>


                        </tr>
						<input
							    type="checkbox"
							    id="modalEditarCurso<%= curso.getId() %>"
							    class="modal-checkbox"
							>
							
						<div class="modal">
							
							    <div class="modal-box">
							
							        <div class="modal-header">
							
							            <h2>
							                Editar curso
							            </h2>
							
							            <label
							                for="modalEditarCurso<%= curso.getId() %>"
							                class="modal-close"
							            >
							                ×
							            </label>
							
							        </div>
							
							        <form
							            action="CursoController"
							            method="post"
							        >
							
							            <input
							                type="hidden"
							                name="item"
							                value="editarCurso"
							            >
							
							            <input
							                type="hidden"
							                name="id"
							                value="<%= curso.getId() %>"
							            >
							
							            <div class="modal-body">
							
							                <div class="form-grid">
							
							                    <!-- NOME -->
							
							                    <div class="form-group form-full">
							
							                        <label>
							                            Nome do curso
							                        </label>
							
							                        <input
							                            type="text"
							                            name="nome"
							                            value="<%= curso.getNome() %>"
							                            maxlength="150"
							                            required
							                        >
							
							                    </div>
							
							
							                    <!-- DESCRIÇÃO -->
							
							                    <div class="form-group form-full">
							
							                        <label>
							                            Descrição
							                        </label>
							
							                        <textarea
							                            name="descricao"
							                            rows="4"
							                        ><%= curso.getDescricao() != null
							                            ? curso.getDescricao()
							                            : "" %></textarea>
							
							                    </div>
							
							
							                    <!-- CARGA HORÁRIA -->
							
							                    <div class="form-group">
							
							                        <label>
							                            Carga horária
							                        </label>
							
							                        <input
							                            type="text"
							                            name="cargaHoraria"
							                            value="<%= curso.getCarga_horaria() %>"
							                            required
							                        >
							
							                    </div>
							
							
							                    <!-- VALOR -->
							
							                    <div class="form-group">
							
							                        <label>
							                            Valor
							                        </label>
							
							                        <input
							                            type="text"
							                            name="valor"
							                            value="<%= curso.getValor() %>"
							                            required
							                        >
							
							                    </div>
							
							                </div>
							
							            </div>
							
							
							            <div class="modal-footer">
							
							                <label
							                    for="modalEditarCurso<%= curso.getId() %>"
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

                        } else {
                    %>


                        <tr>

                            <td
                                colspan="7"
                                style="text-align: center; padding: 40px;"
                            >

                                <div
                                    style="font-size: 35px; margin-bottom: 10px;"
                                >
                                    📚
                                </div>

                                <div
                                    style="font-weight: 600; color: #374151;"
                                >
                                    Nenhum curso encontrado
                                </div>

                                <div
                                    style="
                                        font-size: 13px;
                                        color: #9ca3af;
                                        margin-top: 5px;
                                    "
                                >
                                    Cadastre um novo curso para começar.
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
         MODAL NOVO CURSO
         SEM JAVASCRIPT
    ===================================================== -->

    <input
        type="checkbox"
        id="modalNovoCurso"
        class="modal-checkbox"
    >


    <div class="modal">

        <div class="modal-box">


            <!-- HEADER -->

            <div class="modal-header">

                <h2>
                    Cadastrar novo curso
                </h2>

                <label
                    for="modalNovoCurso"
                    class="modal-close"
                >
                    ×
                </label>

            </div>


            <!-- FORMULÁRIO -->

            <form
                action="CursoController"
                method="post"
            >

                <input
                    type="hidden"
                    name="item"
                    value="cadastraCurso"
                >


                <div class="modal-body">

                    <div class="form-grid">


                        <!-- NOME -->

                        <div class="form-group form-full">

                            <label>
                                Nome do curso
                            </label>

                            <input
                                type="text"
                                name="nome"
                                placeholder="Digite o nome do curso"
                                maxlength="150"
                                required
                            >

                        </div>


                        <!-- DESCRIÇÃO -->

                        <div class="form-group form-full">

                            <label>
                                Descrição
                            </label>

                            <textarea
                                name="descricao"
                                placeholder="Digite a descrição do curso..."
                                rows="4"
                            ></textarea>

                        </div>


                        <!-- CARGA HORÁRIA -->

                        <div class="form-group">

                            <label>
                                Carga horária
                            </label>

                            <input
                                type="text"
                                name="cargaHoraria"
                                placeholder="Ex.: 360"
                                min="1"
                                required
                            >

                        </div>


                        <!-- VALOR -->

                        <div class="form-group">

                            <label>
                                Valor
                            </label>

                            <input
                                type="text"
                                name="valor"
                                placeholder="0,00"
                                min="0"
                                step="0.01"
                                required
                            >

                        </div>


                    </div>

                </div>


                <!-- FOOTER -->

                <div class="modal-footer">


                    <label
                        for="modalNovoCurso"
                        class="btn btn-cancel"
                    >
                        Cancelar
                    </label>


                    <button
                        type="submit"
                        class="btn btn-primary"
                    >
                        Cadastrar curso
                    </button>


                </div>


            </form>


        </div>

    </div>


</body>

</html>