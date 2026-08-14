package controller;

import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Classes.Curso;
//import Classes.Aluno;
//import model.AlunosModel;
import service.CursoService;

@WebServlet("/CursoController")
public class CursoController extends HttpServlet {

	 private static final long serialVersionUID = 1L;

	    //private AlunosModel alunosModel;
	    private CursoService CursoService;

	    public CursoController() {
	        super();
	    }

	    @Override
	    public void init() {
	        //alunosModel = new AlunosModel();
	    	CursoService = new CursoService();
	    }

	    @Override
	    protected void doGet(
	            HttpServletRequest request,
	            HttpServletResponse response)
	            throws ServletException, IOException {
	    	
	    	    String item      = request.getParameter("item");	
	    	    String id 		 = request.getParameter("id");
	    	    String nome      = request.getParameter("nome");
	    	    String status    = request.getParameter("status");
	    	    
	    	    if ("pesquisar".equals(item)) {

	    	        try {

	    	            List<Curso> cursos;

	    	            String nomePesquisa = nome != null ? nome : "";

	    	            Boolean statusBoolean = null;

	    	            if (status != null && !status.isEmpty()) {
	    	                statusBoolean = status.equals("1");
	    	            }

	    	            if (nomePesquisa.isEmpty() && statusBoolean == null) {

	    	                cursos = CursoService.listarCursos();

	    	            } else {

	    	                cursos = CursoService.pesquisarCurso(
	    	                    nomePesquisa,
	    	                    statusBoolean
	    	                );
	    	            }

	    	            request.setAttribute("cursos", cursos);

	    	            if (cursos.isEmpty()) {

	    	                request.setAttribute("msg","Nenhum Curso encontrado!");

	    	            } else {

	    	                request.setAttribute("msg","Pesquisa realizada com sucesso!");
	    	            }
	    	            
	    	            request.getRequestDispatcher("cursos.jsp").forward(request, response);

	    	            return;

	    	        } catch (SQLException e) {

	    	            throw new ServletException(
	    	                "Erro ao pesquisar Curso",
	    	                e
	    	            );
	    	        }
	    	    }
	    	    
	    	    if("statusCurso".equals(item)) {
	    	    	try {
	    	    		boolean alteraStatus = CursoService.alteraStatusCurso(Integer.parseInt(id), Boolean.parseBoolean(status));
	    	    		if(alteraStatus) {
	    	    			request.setAttribute("msg", "Status atualizado com Sucesso!");
	    	    		}else {
	    	    			request.setAttribute("msg", "Houve um problema na atualização do Status");
	    	    		}
	    	    	}catch(SQLException e) {
	    	    		throw new ServletException( "Erro ao mudar Status - Curso", e );
	    	    	}
	    	    	
	    	    	
	    	    }
	    	    
	    	    if("delete".equals(item)) {
	    	    	try {
		    	    	boolean deleteCurso = CursoService.deleteCurso(Integer.parseInt(id));
		    	    	if(deleteCurso) {
		    	    		request.setAttribute("msg", "Curso apagado com sucesso!");
		    	    	}else {
		    	    		request.setAttribute("msg", "Erro ao apagar Curso");
		    	    	}
	    	    	}catch(SQLException e) {
	    	    		throw new ServletException( "Erro ao apagar - Curso", e );
	    	    	}
	    	    }
	    	    
		    	try {
		        	
		        	List<Curso> cursos = CursoService.listarCursos();
		        	request.setAttribute("cursos", cursos);
		        	request.getRequestDispatcher("cursos.jsp").forward(request, response);
		        	return;
		        
		        }catch(SQLException e) {
		        	 throw new ServletException( "Erro ao trazer Cursos", e );
		        }
	    }

	    @Override
	    protected void doPost(
	            HttpServletRequest request,
	            HttpServletResponse response)
	            throws ServletException, IOException {

	        String item          = request.getParameter("item");
	        
	        String id            = request.getParameter("id");
	        String nome 		 = request.getParameter("nome");
	        String descricao	 = request.getParameter("descricao");
	        String cargaHoraria  = request.getParameter("cargaHoraria");
	        String valor		 = request.getParameter("valor");
	        
	        if("editarCurso".equals(item)) {
	        	try {
	        		
	        		boolean updateCurso = CursoService.updateCurso(Integer.parseInt(id), nome, descricao, cargaHoraria, valor);
	        		
	        		if(updateCurso) {
	        			request.setAttribute("msg", "Curso Atualizado com sucesso");
	        		}else {
	        			request.setAttribute("msg", "Houve um problema na atualização do Curso");
	        		}
	        	}catch(SQLException e) {
	        		throw new ServletException( "Erro ao fazer o UPDATE - Cursos", e );
	        	}
	        }
	        
	        if("cadastraCurso".equals(item)) {
	        	try {
	        		
	        		boolean insertCurso = CursoService.insertCurso(nome, descricao, cargaHoraria, valor);
	        		
	        		if(insertCurso) {	        			
	        			request.setAttribute("msg", "Curso adicionando com Sucesso!");	        		
	        		}else {
	        			request.setAttribute("msg", "Houve um problema ao adicionar o Curso!");
	        		}
	        		
	        	}catch(SQLException e) {
	        		throw new ServletException( "Erro ao trazer Cursos", e );
	        	}
	        }
	        
	       try {
	        	
	        	List<Curso> cursos = CursoService.listarCursos();
	        	request.setAttribute("cursos", cursos);
	        	request.getRequestDispatcher("cursos.jsp").forward(request, response);
	        	return;
	        
	        }catch(SQLException e) {
	        	 throw new ServletException( "Erro ao trazer Cursos", e );
	        }
	    }
	
}
