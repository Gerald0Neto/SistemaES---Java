
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Classes.Aluno;
import model.AlunosModel;

@WebServlet("/AlunosController")
public class AlunosController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AlunosModel alunosModel;

    public AlunosController() {
        super();
    }

    @Override
    public void init() {
        alunosModel = new AlunosModel();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    	
    	    String item      = request.getParameter("item");
    		String id        = request.getParameter("id");
    		String status    = request.getParameter("status");
    		String nomeAluno = request.getParameter("nome");
    		String cpf       = request.getParameter("cpf");
    		
    		//PesquisarAluno
    		if ("pesquisar".equals(item)) {

    		    try {

    		        List<Aluno> alunos;

    		        if ((nomeAluno == null || nomeAluno.isEmpty()) && (cpf == null || cpf.isEmpty())) {

    		            alunos = alunosModel.listarAlunos();

    		        } else {

    		            alunos = alunosModel.pesquisarAluno(nomeAluno != null ? nomeAluno : "",cpf != null ? cpf : "", request);
    		        }

    		        request.setAttribute("alunos", alunos);

    		        if (alunos.isEmpty()) {

    		            request.setAttribute("msg","Nenhum aluno encontrado!");

    		        } else {

    		            request.setAttribute("msg", "Pesquisa realizada com sucesso!");
    		        }

    		        request.getRequestDispatcher("alunos.jsp").forward(request, response);
    		        return;

    		    } catch (SQLException e) {

    		        throw new ServletException("Erro ao pesquisar aluno",e);
    		    }
    		}
    		

    		
    		//Altera o Status do Aluno
    		if("statusAluno".equals(item)) {
    			if(!id.isEmpty() && !status.isEmpty()) {
    				try {
	    				boolean updateStatus = alunosModel.alteraStatusAluno(Integer.parseInt(id), Integer.parseInt(status));
	    				if(updateStatus) {
	    					request.setAttribute( "msg", "Status Aluno Atualizado!" );
	    				}else {
	    					request.setAttribute( "msg", "Não foi possivel Atualizar o Status do Aluno!" );
	    				}
    				}catch(SQLException e) {
    					throw new ServletException( "Erro ao mudar Status Aluno", e );
    				}
    			}else {
    				request.setAttribute( "msg", "Aluno não encontrado!" );
    			}
    			
    		}
    		//Delete Aluno
    		if("delete".equals(item)) {
    			if(!id.isEmpty()) {
    				try {
    					boolean deleteAluno = alunosModel.deleteAluno(Integer.parseInt(id));
    					if(deleteAluno) {
    						request.setAttribute("msg", "Aluno deletado com Sucesso!");
    					}else{
    						request.setAttribute("msg", "Não foi possivel Deletar o aluno!");
    					}
    				}catch(SQLException e) {
    					throw new ServletException( "Erro ao mudar Status Aluno", e );
    				}
    			}
    		}

        try {

            List<Aluno> alunos = alunosModel.listarAlunos();

            request.setAttribute("alunos", alunos);
            request.getRequestDispatcher("alunos.jsp").forward(request, response);

        } catch (SQLException e) {

            throw new ServletException( "Erro ao listar alunos",  e );
        }
        
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String cadastraAluno = request.getParameter("alunosCadastra");
        String item          = request.getParameter("item");
        String id            = request.getParameter("id");
		//String status        = request.getParameter("status");
		String nomeAluno     = request.getParameter("nome");
		String cpf           = request.getParameter("cpf");
		String telefone      = request.getParameter("telefone");
		String dataNasc      = request.getParameter("dataNascimento");
		String email         = request.getParameter("email");
        
		//Editar aluno item
        if("editarAluno".equals(item)) {
			try {
				boolean updateDadosAluno = alunosModel.updateAluno(Integer.parseInt(id), nomeAluno, cpf, dataNasc, telefone, email, request);
				
				if(updateDadosAluno) {
					request.setAttribute("msg", "Aluno Atualizado com Sucesso!");
				}else {
					request.setAttribute("msg", "Houve um problema na atualização dos dados do Aluno!");
				}
				request.getRequestDispatcher("alunos.jsp").forward(request, response);
				return;
				
			}catch(SQLException e) {
				throw new ServletException("Erro ao atulizar aluno",e);
			}
		}

        //Cadastra Aluno
        if ("cadastraAluno".equals(cadastraAluno)) {     

            try {

                boolean insertValida =  alunosModel.insertAlunos(nomeAluno, cpf, dataNasc, telefone, email, request );

                if (insertValida) {

                    request.setAttribute( "msg", "Aluno adicionado com sucesso!" );

                } else {

                    request.setAttribute( "msg",  "Falha ao adicionar aluno!" );
                }

                // Busca novamente os alunos
                List<Aluno> alunos = alunosModel.listarAlunos();

                request.setAttribute( "alunos", alunos );
                request.getRequestDispatcher("alunos.jsp").forward(request, response);
                return;

            } catch (SQLException e) {

                throw new ServletException( "Erro ao cadastrar aluno", e );
            }
        }

        // Se não for uma operação conhecida
        response.sendRedirect("AlunosController");
    }
}

