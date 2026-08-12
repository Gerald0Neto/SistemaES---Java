
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import Classes.Aluno;
import model.AlunosModel;
import model.LoginModel;

@WebServlet("/Login")
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private LoginModel loginModel;
    private AlunosModel alunosModel;

    public Login() {
        super();
    }

    @Override
    public void init() {
        loginModel = new LoginModel();
        alunosModel = new AlunosModel();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            List<Aluno> alunos =  alunosModel.listarAlunos();

            request.setAttribute( "alunos", alunos );

            request.getRequestDispatcher( "alunos.jsp" ).forward(request, response);

        } catch (SQLException e) {

            throw new ServletException( "Erro ao listar alunos", e );
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");

        String senha = request.getParameter("senha");

        //HttpSession session = request.getSession();

        try {

            boolean usuarioValido = loginModel.verificarUsuario( email, senha, request );

            if (usuarioValido) {

                // Busca os alunos no banco
                List<Aluno> alunos = alunosModel.listarAlunos();

                // Envia os alunos para o JSP
                request.setAttribute( "alunos", alunos );

                // Mensagem de boas-vindas
                request.setAttribute( "msg", "Seja bem-vindo!" );

                // Abre a página já com os alunos
                request.getRequestDispatcher( "alunos.jsp" ).forward(request, response);

                return;

            } else {

                request.setAttribute( "msg", "Usuário ou senha inválidos"  );

                request.getRequestDispatcher( "index.jsp").forward(request, response);

                return;
            }

        } catch (Exception e) {

            throw new ServletException( "Erro ao realizar login", e );
        }
    }
}

