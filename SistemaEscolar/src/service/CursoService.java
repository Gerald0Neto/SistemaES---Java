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
    
    public boolean insertCurso(String nome, String descricao, String cargaHoraria, String valor) throws SQLException {
    	return CursoModel.insertCurso(nome, descricao, cargaHoraria, valor);
    }
    
    public boolean alteraStatusCurso(int id, boolean status) throws SQLException {
    	return CursoModel.alteraStatusCurso(id, status);
    }
    
    public boolean deleteCurso(int id) throws SQLException {
    	return CursoModel.deleteCurso(id);
    }
    
    public boolean updateCurso(int id, String nome, String descricao, String cargaHoraria, String valor) throws SQLException {
    	return CursoModel.updateCurso(id, nome, descricao, cargaHoraria, valor);
    }
    
    public List<Curso> pesquisarCurso(String nome, Boolean status) throws SQLException{
    	return CursoModel.pesquisarCurso(nome, status);
    }
}
