package service;

import model.CursoModel;

import java.sql.SQLException;
import java.util.List;

import Classes.Curso;

public class CursoService {
	
	private CursoModel CursoModel;

    public CursoService() {
    	CursoModel = new CursoModel();
    }
    
    public List<Curso> listarCursos() throws SQLException {

        return CursoModel.listarCursos();

    }
}
