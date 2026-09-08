package service;

import model.AlunosModel;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import Classes.Aluno;

public class AlunoService {
	
	private AlunosModel AlunosModel;
	
	public AlunoService() {
		AlunosModel = new AlunosModel();
	}
	
	public boolean updateAluno( 
			int id,
			String nome,
            String cpf,
            String dataNasc,
            String telefone,
            String email,
            HttpServletRequest request) throws SQLException{
		
		return AlunosModel.updateAluno(id, nome, cpf, dataNasc, telefone, email, request);
		
	}
	
	public List<Aluno> pesquisarAluno(
			String nome,
	        String cpf,
	        HttpServletRequest request) throws SQLException {
		
		return AlunosModel.pesquisarAluno(nome, cpf, request);
		
	}
	

}
