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
	    	
	    	    //String item      = request.getParameter("item");	 
		    	try {
		        	
		        	List<Curso> cursos = CursoService.listarCursos();
		        	request.setAttribute("cursos", cursos);
		        	request.getRequestDispatcher("cursos.jsp").forward(request, response);
		        
		        }catch(SQLException e) {
		        	 throw new ServletException( "Erro ao trazer Cursos", e );
		        }
	    }

	    @Override
	    protected void doPost(
	            HttpServletRequest request,
	            HttpServletResponse response)
	            throws ServletException, IOException {

	        //String item          = request.getParameter("item");
	        
	        
	        response.sendRedirect("CursoController");
	    }
	
}
