package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Classes.Curso;
import service.CursoService;

@WebServlet("/CursoController")
public class CursoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CursoService cursoService;

    @Override
    public void init() {
        cursoService = new CursoService();
    }

    // =========================================================
    // GET
    // =========================================================

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String item = request.getParameter("item");

        if ("pesquisar".equals(item)) {

            pesquisar(request, response);

        } else if ("statusCurso".equals(item)) {

            alterarStatus(request, response);

        } else if ("delete".equals(item)) {

            deletar(request, response);

        } else {

            listar(request, response);
        }
    }

    // =========================================================
    // POST
    // =========================================================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String item = request.getParameter("item");

        if ("editarCurso".equals(item)) {

            editar(request, response);

        } else if ("cadastraCurso".equals(item)) {

            cadastrar(request, response);

        } else {

            listar(request, response);
        }
    }

    // =========================================================
    // MÉTODOS DO CONTROLLER
    // =========================================================

    private void listar(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            List<Curso> cursos = cursoService.listarCursos();

            request.setAttribute("cursos", cursos);

            request.getRequestDispatcher("cursos.jsp")
                   .forward(request, response);

        } catch (SQLException e) {

            throw new ServletException(
                "Erro ao trazer Cursos",
                e
            );
        }
    }

    private void pesquisar(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String status = request.getParameter("status");

        try {

            String nomePesquisa = nome != null ? nome : "";

            Boolean statusBoolean = null;

            if (status != null && !status.isEmpty()) {
                statusBoolean = status.equals("1");
            }

            List<Curso> cursos;

            if (nomePesquisa.isEmpty() && statusBoolean == null) {

                cursos = cursoService.listarCursos();

            } else {

                cursos = cursoService.pesquisarCurso(
                    nomePesquisa,
                    statusBoolean
                );
            }

            request.setAttribute("cursos", cursos);

            if (cursos.isEmpty()) {

                request.setAttribute(
                    "msg",
                    "Nenhum Curso encontrado!"
                );

            } else {

                request.setAttribute(
                    "msg",
                    "Pesquisa realizada com sucesso!"
                );
            }

            request.getRequestDispatcher("cursos.jsp")
                   .forward(request, response);

        } catch (SQLException e) {

            throw new ServletException(
                "Erro ao pesquisar Curso",
                e
            );
        }
    }

    private void alterarStatus(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String status = request.getParameter("status");

        try {

            boolean alterou = cursoService.alteraStatusCurso(
                Integer.parseInt(id),
                Boolean.parseBoolean(status)
            );

            if (alterou) {

                request.setAttribute(
                    "msg",
                    "Status atualizado com sucesso!"
                );

            } else {

                request.setAttribute(
                    "msg",
                    "Houve um problema na atualização do Status."
                );
            }

            listar(request, response);

        } catch (SQLException e) {

            throw new ServletException(
                "Erro ao mudar Status do Curso",
                e
            );
        }
    }

    private void deletar(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        try {

            boolean deletou = cursoService.deleteCurso(
                Integer.parseInt(id)
            );

            if (deletou) {

                request.setAttribute(
                    "msg",
                    "Curso apagado com sucesso!"
                );

            } else {

                request.setAttribute(
                    "msg",
                    "Erro ao apagar Curso."
                );
            }

            listar(request, response);

        } catch (SQLException e) {

            throw new ServletException(
                "Erro ao apagar Curso",
                e
            );
        }
    }

    private void cadastrar(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String cargaHoraria = request.getParameter("cargaHoraria");
        String valor = request.getParameter("valor");

        try {

            boolean inseriu = cursoService.insertCurso(
                nome,
                descricao,
                cargaHoraria,
                valor
            );

            if (inseriu) {

                request.setAttribute(
                    "msg",
                    "Curso adicionado com sucesso!"
                );

            } else {

                request.setAttribute(
                    "msg",
                    "Houve um problema ao adicionar o Curso!"
                );
            }

            listar(request, response);

        } catch (SQLException e) {

            throw new ServletException(
                "Erro ao cadastrar Curso",
                e
            );
        }
    }

    private void editar(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String cargaHoraria = request.getParameter("cargaHoraria");
        String valor = request.getParameter("valor");

        try {

            boolean atualizou = cursoService.updateCurso(
                Integer.parseInt(id),
                nome,
                descricao,
                cargaHoraria,
                valor
            );

            if (atualizou) {

                request.setAttribute(
                    "msg",
                    "Curso atualizado com sucesso!"
                );

            } else {

                request.setAttribute(
                    "msg",
                    "Houve um problema na atualização do Curso."
                );
            }

            listar(request, response);

        } catch (SQLException e) {

            throw new ServletException(
                "Erro ao fazer UPDATE do Curso",
                e
            );
        }
    }
}